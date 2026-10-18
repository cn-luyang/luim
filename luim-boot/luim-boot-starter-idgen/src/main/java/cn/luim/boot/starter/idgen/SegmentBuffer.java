package cn.luim.boot.starter.idgen;

import cn.luim.boot.starter.base.model.Result;
import cn.luim.boot.starter.base.utils.id.IdUtil;
import cn.luim.boot.starter.idgen.dubbo.IdGenRpcService;
import cn.luim.boot.starter.idgen.dubbo.response.GetSegmentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 号段双缓冲缓存
 *
 * @author yang.lu
 */
public class SegmentBuffer {

	private static final Logger logger = LoggerFactory.getLogger(SegmentBuffer.class);

	/** 预加载阈值：当前段剩余低于该比例时触发预加载 */
	private static final double PRELOAD_THRESHOLD = 0.1D;
	/** 切换锁：保证 current ↔ standby 切换的串行化 */
	private final ReentrantLock switchLock = new ReentrantLock();
	/** 当前发号号段 */
	private final AtomicReference<Segment> current = new AtomicReference<>();
	/** 备用号段，当前段耗尽或快耗尽时使用 */
	private final AtomicReference<Segment> standby = new AtomicReference<>();
	/** RPC 调用标记， true:正在进行 RPC 拉取 */
	private final AtomicBoolean rpcCallMarker = new AtomicBoolean(false);
	/** 号段预加载线程池（单线程串行拉取）*/
	private static final ExecutorService PRELOAD_EXECUTOR = new ThreadPoolExecutor(
		1, 1, 0L, TimeUnit.MILLISECONDS,
		new SynchronousQueue<>(),
		r -> {
			Thread t = new Thread(r, "idgen-loader");
			t.setDaemon(true); // 线程工厂：守护线程，不阻止 JVM 退出
			return t;
		},
		new ThreadPoolExecutor.DiscardPolicy() // 拒绝策略：丢弃，由双重检查兜底，丢弃安全
	);

	private final IdGenRpcService idGenRpcService;

	public SegmentBuffer(IdGenRpcService idGenRpcService) {
		this.idGenRpcService = idGenRpcService;
	}

	public long nextId() {
		Segment currentSegment = current.get();
		if (currentSegment != null) {
			long id = currentSegment.nextId();
			if (id > 0) {
				// 容量低于阈值时，触发后台异步预取备用号段
				loadStandbySegment(currentSegment);
				return id;
			}
		}

		switchToStandby();

		currentSegment = current.get();
		if (currentSegment != null) {
			long id = currentSegment.nextId();
			if (id > 0) {
				return id;
			}
		}

		return IdUtil.getSnowflakeNextId();
	}

	/**
	 * current 耗尽后切换到 standby。 如 standby 为空则同步兜底拉取
	 */
	private void switchToStandby() {
		switchLock.lock();
		try {
			// 双重检查：若其他线程已经完成了切换，直接获取
			Segment currentSegment = current.get();
			if (null != currentSegment) {
				long nextId = currentSegment.nextId();
				if (nextId > 0) {
					return;
				}
			}

			// 尝试获取备用号段
			Segment standbySegment = standby.getAndSet(null);
			if (null == standbySegment) {
				logger.warn("备用号段为空，触发同步阻塞拉取远程号段");
				standbySegment = getSegment(currentSegment);
			}

			if (standbySegment != null) {
				current.set(standbySegment);
			}
		} finally {
			switchLock.unlock();
		}
	}

	/**
	 * 加载备用号段
	 */
	private void loadStandbySegment(Segment currentSegment) {
		if (standby.get() != null) {
			return;
		}

		// 检查剩余容量是否 大于 PRELOAD_THRESHOLD
		if (currentSegment.availableQuantity() > currentSegment.capacity() * PRELOAD_THRESHOLD) {
			return;
		}

		PRELOAD_EXECUTOR.execute(() -> {
			if (null == standby.get()) {
				Segment loadedSegment = getSegment(current.get());
				if (loadedSegment != null) {
					standby.compareAndSet(null, loadedSegment);
				}
			}
		});
	}

	/**
	 * 统一收敛 RPC 调用、状态抢占与校验逻辑
	 */
	private Segment getSegment(Segment segment) {
		// 抢占全局 RPC 资格：没抢到说明其他线程正在拉取，直接返回 null（丢弃/跳过）
		if (!rpcCallMarker.compareAndSet(false, true)) {
			return null;
		}

		try {
			Result<GetSegmentResponse> result = idGenRpcService.getSegment(null);
			if (result.noSuccess()) {
				return null;
			}
			GetSegmentResponse getSegmentResponse = result.getData();
			return new Segment(getSegmentResponse.getStart(), getSegmentResponse.getEnd(), null);
		} catch (Exception e) {
			logger.error("远程拉取号段异常", e);
			return null;
		} finally {
			rpcCallMarker.set(false);
		}
	}

//	/**
//	 * 号段安全校验：防止服务端乱序、回拨或下发错误区间
//	 */
//	private void validateSegment(Segment oldSegment, SegmentResponse response) {
//		if (response == null || response.getStart() == null || response.getEnd() == null) {
//			throw new IllegalArgumentException("RPC 下发的号段内容为空");
//		}
//		if (response.getStart() > response.getEnd()) {
//			throw new IllegalArgumentException("非法号段：起始值大于结束值");
//		}
//		if (oldSegment != null && response.getStart() <= oldSegment.getStartValue()) {
//			logger.warn("【号段安全警报】新拉取的号段起始值 [{}] 小于等于旧号段起始值 [{}]，请检查服务端并发或时钟状态",
//				response.getStart(), oldSegment.getStartValue());
//		}
//	}
}
