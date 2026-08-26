package cn.luim.boot.starter.base.utils.id;

import cn.luim.boot.starter.base.utils.NetUtil;
import cn.luim.boot.starter.base.utils.constant.StringPool;
import io.github.robsonkades.uuidv7.UUIDv7;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ID生成器工具类
 *
 * @author yang.lu
 */
@UtilityClass
public class IdUtil {

	/**
	 * 单例对象缓存池 (Key -> Snowflake)
	 */
	private static final Map<String, Snowflake> SNOWFLAKE_POOL = new ConcurrentHashMap<>();

	/**
	 * 默认 Snowflake 缓存键
	 */
	private static final String DEFAULT_SNOWFLAKE_KEY = "DEFAULT";

	/**
	 * 获取默认参数的 Snowflake 全局单例
	 */
	public static Snowflake getSnowflake() {
		return SNOWFLAKE_POOL.computeIfAbsent(DEFAULT_SNOWFLAKE_KEY, k -> new Snowflake());
	}

	/**
	 * 根据指定参数获取或创建 Snowflake 单例 (相同参数复用同一实例)
	 *
	 * @param workerId     工作机器 ID
	 * @param dataCenterId 数据中心 ID
	 * @return Snowflake 单例
	 */
	public static Snowflake getSnowflake(long workerId, long dataCenterId) {
		String key = workerId + StringPool.COLON + dataCenterId;
		return SNOWFLAKE_POOL.computeIfAbsent(key, k -> new Snowflake(workerId, dataCenterId));
	}

	/**
	 * 生成 UUIDv7 字符串
	 *
	 * @return 36 位的 UUIDv7 字符串
	 */
	public static String randomUUID() {
		return UUIDv7.randomUUIDString();
	}

	/**
	 * 生成 UUIDv7 字符串
	 *
	 * @return 32 位无横杠的 UUIDv7 字符串
	 */
	public static String simpleUUID() {
		return randomUUID().replace("-", "");
	}

	/**
	 * 生成默认长度（21位）的 NanoId
	 *
	 * @return URL 安全的随机 ID 字符串
	 */
	public static String nanoId() {
		return NanoId.randomNanoId();
	}

	/**
	 * 生成指定长度的 NanoId
	 *
	 * @param size ID 长度（必须 &gt; 0）
	 * @return URL 安全的随机 ID 字符串
	 * @throws IllegalArgumentException 当 size &lt;= 0 时抛出
	 */
	public static String nanoId(int size) {
		return NanoId.randomNanoId(size);
	}

	/**
	 * 根据数据中心 ID 和进程 PID 计算 Worker ID
	 *
	 * @param datacenterId 数据中心 ID
	 * @param maxWorkerId  允许的最大 Worker ID
	 * @return 节点 Worker ID
	 */
	public static long getWorkerId(long datacenterId, long maxWorkerId) {
		Assert.isTrue(maxWorkerId > 0, "maxWorkerId must be > 0");

		// 获取当前 JVM 进程 PID
		long pid = ProcessHandle.current().pid();

		// 结合数据中心 ID 与 PID 计算哈希值，映射到合法 ID 范围内 [0, maxWorkerId]
		String compositeKey = datacenterId + StringPool.SLASH + pid;

		// hashCode() 逻辑与 bitmask 处理，保证结果为非负数
		return (compositeKey.hashCode() & 0xFFFF) % (maxWorkerId + 1);
	}

	/**
	 * 获取数据中心 ID (DataCenter ID)
	 * <p>
	 * 逻辑说明：
	 * 1. 优先获取本机首个非环回网络接口（网卡）的 MAC 地址。
	 * 2. 取 MAC 地址的最后两个字节组合进行位移运算生成散列值。
	 * 3. 对 (maxDatacenterId + 1) 取模，确保返回值处于 [0, maxDatacenterId] 范围内。
	 * 4. 若获取 MAC 地址失败，默认回退返回 1L。
	 *
	 * @param maxDatacenterId 允许的最大数据中心 ID (必须大于 0)
	 * @return 数据中心 ID
	 */
	public static long getDataCenterId(long maxDatacenterId) {
		// 参数校验 (Spring Framework 7 标准校验)
		Assert.isTrue(maxDatacenterId > 0, () -> "maxDatacenterId must be > 0");

		long id = 1L;
		byte[] mac = NetUtil.getLocalHardwareAddress();

		// 根据 MAC 地址后 2 个字节通过位运算计算 ID
		if (mac != null && mac.length >= 2) {
			long byte1 = mac[mac.length - 2] & 0xFFL;
			long byte2 = mac[mac.length - 1] & 0xFFL;

			// 组合后两位字节并右移 6 位生成 hash 种子
			id = ((byte1 | (byte2 << 8)) >> 6);

			// 取模限定在 [0, maxDatacenterId] 范围
			id = Math.abs(id) % (maxDatacenterId + 1);
		}

		return id;
	}

	/**
	 * 获取下一个 Snowflake ID (long 型)
	 *
	 * @return 64 位 long 型 ID
	 */
	public static long getSnowflakeNextId() {
		return getSnowflake().nextId();
	}

	/**
	 * 获取下一个 Snowflake ID (字符串形式)
	 *
	 * @return 字符串形式 ID
	 */
	public static String getSnowflakeNextIdStr() {
		return getSnowflake().nextIdStr();
	}

	/**
	 * 获取指定节点的下一个 Snowflake ID (64 位整型)
	 */
	public static long getSnowflakeNextId(long workerId, long dataCenterId) {
		return getSnowflake(workerId, dataCenterId).nextId();
	}

	/**
	 * 获取指定节点的下一个 Snowflake ID (字符串形式)
	 */
	public static String getSnowflakeNextIdStr(long workerId, long dataCenterId) {
		return getSnowflake(workerId, dataCenterId).nextIdStr();
	}
}
