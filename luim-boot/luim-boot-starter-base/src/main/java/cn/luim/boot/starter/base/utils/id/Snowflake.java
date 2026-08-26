package cn.luim.boot.starter.base.utils.id;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.Assert;

import java.io.Serial;
import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.util.random.RandomGenerator;

/**
 * Snowflake 算法分布式唯一 ID 生成器
 * <p>
 * 结构说明 (64bits long)：
 * 1 位标识 (固定0) + 41 位时间戳 + 5 位数据中心 ID + 5 位工作机器 ID + 12 位自增序列号
 *
 * @author yang.lu
 */
public class Snowflake implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/* -------------------------------- 默认常量定义 -------------------------------- */

	/** 默认起始时间戳：2010-11-04 01:42:54 GMT */
	public static final long DEFAULT_TWEPOCH = 1288834974657L;

	/** 默认允许的最大时钟回拨毫秒数 (2000ms = 2s) */
	public static final long DEFAULT_TIME_OFFSET = 2000L;

	/* -------------------------------- 位长与掩码计算 -------------------------------- */

	/** 机器节点 ID 占用的位数 (5bit) */
	private static final long WORKER_ID_BITS = 5L;

	/** 数据中心 ID 占用的位数 (5bit) */
	private static final long DATA_CENTER_ID_BITS = 5L;

	/** 序列号占用的位数 (12bit) */
	private static final long SEQUENCE_BITS = 12L;

	/** 支持的最大机器节点 ID：31 */
	public static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

	/** 支持的最大数据中心节点 ID：31 */
	public static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

	/** 序列号最大掩码：4095 (0~4095) */
	private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

	/* -------------------------------- 位运算移位偏移量 -------------------------------- */

	/** 机器节点 ID 左移位数 (12位) */
	private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

	/** 数据中心 ID 左移位数 (17位) */
	private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

	/** 时间戳左移位数 (22位) */
	private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

	/* -------------------------------- 实例不可变配置 -------------------------------- */

	/** 基准起始时间戳 (毫秒) */
	private final long twepoch;

	/** 工作机器 ID (0 ~ 31) */
	private final long workerId;

	/** 数据中心 ID (0 ~ 31) */
	private final long dataCenterId;

	/** 是否使用 JDK 系统标准 Clock */
	private final boolean useSystemClock;

	/** 容忍的时钟回拨最大毫秒数 */
	private final long timeOffset;

	/** 低频模式下序列号的随机生成上限 (0 表示关闭随机) */
	private final long randomSequenceLimit;

	/** Java 21 高性能伪随机数生成器 */
	private final RandomGenerator randomGenerator = RandomGenerator.getDefault();

	/** JDK 系统时钟引用 */
	private final Clock clock = Clock.systemUTC();

	/* -------------------------------- 运行期状态变量 -------------------------------- */

	/** 毫秒内自增序列号 (0 ~ 4095) */
	private long sequence = 0L;

	/** 上一次生成 ID 的时间戳 */
	private long lastTimestamp = -1L;

	/* -------------------------------- 构造函数 -------------------------------- */

	public static final long DEFAULT_DATACENTER_ID = IdUtil.getDataCenterId(Snowflake.MAX_DATA_CENTER_ID);
	public static final long DEFAULT_WORKER_ID = IdUtil.getWorkerId(DEFAULT_DATACENTER_ID, Snowflake.MAX_WORKER_ID);

	/**
	 * 默认构造函数：自动获取机器 ID 和数据中心 ID
	 */
	public Snowflake() {
		this(DEFAULT_WORKER_ID, DEFAULT_DATACENTER_ID);
	}

	/**
	 * 构造函数：指定机器 ID
	 *
	 * @param workerId 终端机器 ID
	 */
	public Snowflake(long workerId) {
		this(workerId, DEFAULT_DATACENTER_ID);
	}

	/**
	 * 构造函数：指定机器 ID 和数据中心 ID
	 *
	 * @param workerId     终端机器 ID
	 * @param dataCenterId 数据中心 ID
	 */
	public Snowflake(long workerId, long dataCenterId) {
		this(workerId, dataCenterId, false);
	}

	/**
	 * 构造函数：指定机器 ID、数据中心 ID 及时钟模式
	 *
	 * @param workerId         终端机器 ID
	 * @param dataCenterId     数据中心 ID
	 * @param isUseSystemClock 是否使用 System.currentTimeMillis()
	 */
	public Snowflake(long workerId, long dataCenterId, boolean isUseSystemClock) {
		this(null, workerId, dataCenterId, isUseSystemClock, DEFAULT_TIME_OFFSET, 0L);
	}

	/** Instant 参数构造函数 */
	public Snowflake(Instant epochInstant, long workerId, long dataCenterId, boolean isUseSystemClock) {
		this(epochInstant, workerId, dataCenterId, isUseSystemClock, DEFAULT_TIME_OFFSET, 0L);
	}

	/** Instant 参数构造函数 (带回拨时间) */
	public Snowflake(Instant epochInstant, long workerId, long dataCenterId, boolean isUseSystemClock, long timeOffset) {
		this(epochInstant, workerId, dataCenterId, isUseSystemClock, timeOffset, 0L);
	}

	/**
	 * 全参构造函数
	 *
	 * @param epochInstant        初始化基准时间起点（null 则使用默认基准时间）
	 * @param workerId            工作机器 ID (0 ~ 31)
	 * @param dataCenterId        数据中心 ID (0 ~ 31)
	 * @param isUseSystemClock    是否优先使用直接系统时钟
	 * @param timeOffset          允许的最大时钟回拨毫秒数
	 * @param randomSequenceLimit 序列号随机上限（避免低频调用时 ID 结尾恒为偶数，0 表示关闭）
	 */
	public Snowflake(Instant epochInstant, long workerId, long dataCenterId,
					 boolean isUseSystemClock, long timeOffset, long randomSequenceLimit) {
		// 参数范围合法性校验 (Spring Framework Assert)
		Assert.isTrue(workerId >= 0 && workerId <= MAX_WORKER_ID,
			() -> "workerId must be between 0 and %d".formatted(MAX_WORKER_ID));
		Assert.isTrue(dataCenterId >= 0 && dataCenterId <= MAX_DATA_CENTER_ID,
			() -> "dataCenterId must be between 0 and %d".formatted(MAX_DATA_CENTER_ID));
		Assert.isTrue(randomSequenceLimit >= 0 && randomSequenceLimit <= SEQUENCE_MASK,
			() -> "randomSequenceLimit must be between 0 and %d".formatted(SEQUENCE_MASK));

		this.twepoch = (epochInstant != null) ? epochInstant.toEpochMilli() : DEFAULT_TWEPOCH;
		this.workerId = workerId;
		this.dataCenterId = dataCenterId;
		this.useSystemClock = isUseSystemClock;
		this.timeOffset = timeOffset;
		this.randomSequenceLimit = randomSequenceLimit;
	}

	/**
	 * 生成下一个分布式唯一 ID (线程安全)
	 *
	 * @return 64 位整型 Snowflake ID
	 */
	public synchronized long nextId() {
		long timestamp = genTime();

		// 处理时钟回拨
		if (timestamp < this.lastTimestamp) {
			long offset = this.lastTimestamp - timestamp;
			if (offset < timeOffset) {
				// 允许范围内的回拨：复用上一次记录的时间戳，避免异常中断
				timestamp = lastTimestamp;
			} else {
				// 超出容忍范围：直接抛出异常，拒绝生成 ID
				throw new IllegalStateException("Clock moved backwards. Refusing to generate id for %d ms".formatted(offset));
			}
		}

		// 毫秒内与跨毫秒逻辑处理
		if (timestamp == this.lastTimestamp) {
			// 同一毫秒内：自增序列号，并与掩码做按位与，超出 4095 则归零
			this.sequence = (this.sequence + 1) & SEQUENCE_MASK;
			if (this.sequence == 0) {
				// 当前毫秒序列已满，阻塞等待进入下一毫秒
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			// 进入全新毫秒：若配置了随机上限，则设置随机初始值；否则重置为 0
			this.sequence = (randomSequenceLimit > 1)
				? randomGenerator.nextLong(randomSequenceLimit)
				: 0L;
		}

		// 更新上次生成 ID 的时间戳记录
		this.lastTimestamp = timestamp;

		// 按位拼接生成最终的 64 位 long 型 ID
		return ((timestamp - twepoch) << TIMESTAMP_LEFT_SHIFT)
			| (dataCenterId << DATA_CENTER_ID_SHIFT)
			| (workerId << WORKER_ID_SHIFT)
			| sequence;
	}

	/**
	 * 生成下一个分布式唯一 ID（字符串形式）
	 *
	 * @return ID 字符串
	 */
	public String nextIdStr() {
		return Long.toString(nextId());
	}

	/* -------------------------------- 辅助解析方法 -------------------------------- */

	/**
	 * 从生成的 Snowflake ID 中反解析出 Worker ID
	 *
	 * @param id Snowflake ID
	 * @return 机器节点 ID
	 */
	public long getWorkerId(long id) {
		return (id >> WORKER_ID_SHIFT) & MAX_WORKER_ID;
	}

	/**
	 * 从生成的 Snowflake ID 中反解析出 DataCenter ID
	 *
	 * @param id Snowflake ID
	 * @return 数据中心节点 ID
	 */
	public long getDataCenterId(long id) {
		return (id >> DATA_CENTER_ID_SHIFT) & MAX_DATA_CENTER_ID;
	}

	/**
	 * 从生成的 Snowflake ID 中反解析出生成时间戳 (毫秒)
	 *
	 * @param id Snowflake ID
	 * @return 生成时间戳
	 */
	public long getGenerateDateTime(long id) {
		return (id >> TIMESTAMP_LEFT_SHIFT) + twepoch;
	}

	/**
	 * 根据时间戳范围计算生成 ID 的起止范围 (默认忽略机器与数据中心占位)
	 *
	 * @param timestampStart 开始时间戳
	 * @param timestampEnd   结束时间戳
	 * @return Pair<起点ID, 终点ID>
	 */
	public Pair<Long, Long> getIdScopeByTimestamp(long timestampStart, long timestampEnd) {
		return getIdScopeByTimestamp(timestampStart, timestampEnd, true);
	}

	/**
	 * 根据时间戳范围计算生成 ID 的起止范围
	 *
	 * @param timestampStart        开始时间戳
	 * @param timestampEnd          结束时间戳
	 * @param ignoreCenterAndWorker 是否忽略机器和数据中心占位（true 可获得全局范围起止点）
	 * @return Pair<起点ID, 终点ID>
	 */
	public Pair<Long, Long> getIdScopeByTimestamp(long timestampStart, long timestampEnd, boolean ignoreCenterAndWorker) {
		long startTimeMinId = (timestampStart - twepoch) << TIMESTAMP_LEFT_SHIFT;
		long endTimeMinId = (timestampEnd - twepoch) << TIMESTAMP_LEFT_SHIFT;

		if (ignoreCenterAndWorker) {
			long endId = endTimeMinId | ~(-1L << TIMESTAMP_LEFT_SHIFT);
			return Pair.of(startTimeMinId, endId);
		} else {
			long mask = (dataCenterId << DATA_CENTER_ID_SHIFT) | (workerId << WORKER_ID_SHIFT);
			long startId = startTimeMinId | mask;
			long endId = endTimeMinId | mask | SEQUENCE_MASK;
			return Pair.of(startId, endId);
		}
	}

	/* -------------------------------- 私有辅助方法 -------------------------------- */

	/**
	 * 循环自旋等待，直到获取到下一毫秒的时间戳
	 *
	 * @param lastTimestamp 上一次的时间戳
	 * @return 下一毫秒的时间戳
	 */
	private long tilNextMillis(long lastTimestamp) {
		long timestamp = genTime();
		while (timestamp == lastTimestamp) {
			timestamp = genTime();
		}
		if (timestamp < lastTimestamp) {
			throw new IllegalStateException("Clock moved backwards. Refusing to generate id for %d ms".formatted(lastTimestamp - timestamp));
		}
		return timestamp;
	}

	/**
	 * 获取当前系统时间戳 (毫秒)
	 *
	 * @return 时间戳
	 */
	private long genTime() {
		return this.useSystemClock ? System.currentTimeMillis() : clock.millis();
	}
}
