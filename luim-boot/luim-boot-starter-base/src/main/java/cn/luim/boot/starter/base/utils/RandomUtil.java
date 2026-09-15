package cn.luim.boot.starter.base.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数工具类
 *
 * @author yang.lu
 */
public class RandomUtil {

	/**
	 * 获取当前线程的 {@link ThreadLocalRandom} 实例
	 *
	 * @return ThreadLocalRandom 实例
	 */
	public static ThreadLocalRandom getRandom() {
		return ThreadLocalRandom.current();
	}

	/**
	 * 生成指定范围内的随机整数（默认左闭右开）
	 *
	 * @param minInclude 最小值（包含）
	 * @param maxExclude 最大值（不包含）
	 * @return 随机整数，范围 [minInclude, maxExclude)
	 */
	public static int randomInt(final int minInclude, final int maxExclude) {
		return randomInt(minInclude, maxExclude, true, false);
	}

	/**
	 * 生成指定范围内的随机整数，支持自定义端点包含性
	 *
	 * @param min        下限
	 * @param max        上限
	 * @param includeMin 是否包含下限
	 * @param includeMax 是否包含上限
	 * @return 随机整数
	 * @throws IllegalArgumentException 当 min > max 或区间无效时抛出
	 */
	public static int randomInt(int min, int max, final boolean includeMin, final boolean includeMax) {
		if (min > max) {
			throw new IllegalArgumentException("min must be <= max");
		}
		if (!includeMin) {
			min++;
		}
		if (includeMax) {
			max++;
		}
		if (min >= max) {
			throw new IllegalArgumentException("Invalid range after boundary adjustment");
		}
		return getRandom().nextInt(min, max);
	}
}
