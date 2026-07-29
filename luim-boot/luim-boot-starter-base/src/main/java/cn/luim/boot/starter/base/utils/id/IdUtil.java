package cn.luim.boot.starter.base.utils.id;

import io.github.robsonkades.uuidv7.UUIDv7;
import lombok.experimental.UtilityClass;

/**
 * ID生成器工具类
 *
 * @author yang.lu
 */
@UtilityClass
public class IdUtil {

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
}
