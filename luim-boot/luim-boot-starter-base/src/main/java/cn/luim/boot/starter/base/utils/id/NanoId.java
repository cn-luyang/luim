package cn.luim.boot.starter.base.utils.id;

import java.security.SecureRandom;
import java.util.Random;

/**
 * NanoId 生成工具类
 *
 * @author yang.lu
 */
public class NanoId {

	/** 默认随机数生成器 */
	private static final SecureRandom DEFAULT_NUMBER_GENERATOR = new SecureRandom();

	/** 默认随机字母表 */
	private static final char[] DEFAULT_ALPHABET =
			"_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	/** 默认ID长度 */
	public static final int DEFAULT_SIZE = 21;

	/**
	 * 生成默认长度的 NanoId（21位）
	 *
	 * @return 随机生成的 NanoId 字符串
	 */
	public static String randomNanoId() {
		return randomNanoId(DEFAULT_SIZE);
	}

	/**
	 * 生成指定长度的 NanoId
	 *
	 * @param size 生成的ID长度，必须大于0
	 * @return 随机生成的 NanoId 字符串
	 */
	public static String randomNanoId(final int size) {
		return randomNanoId(null, null, size);
	}

	/**
	 * 生成 NanoId ，支持自定义随机数生成器和字母表
	 *
	 * @param random   随机数生成器，可为null（将使用默认的SecureRandom）
	 * @param alphabet 自定义字母表，可为null（将使用默认字母表）
	 * @param size     生成的ID长度，必须大于0
	 * @return 随机生成的 NanoId 字符串
	 * @throws IllegalArgumentException 当 alphabet 为空或长度 >= 256，或 size <= 0 时抛出
	 *
	 * <p><b>算法说明：</b></p>
	 * <ul>
	 *   <li>mask: 用于将随机字节映射到字母表索引的位掩码。
	 *            计算方式为：找到 >= (alphabet.length - 1) 的最小 2^k - 1</li>
	 *   <li>step: 每次生成的随机字节数。使用 1.6 倍的冗余因子，以减少重试概率，
	 *            在性能和效率之间取得平衡</li>
	 *   <li>循环生成随机字节，通过 mask 过滤出有效索引，添加到结果中，
	 *            直到达到目标长度</li>
	 * </ul>
	 */
	public static String randomNanoId(Random random, char[] alphabet, final int size) {
		if (random == null) {
			random = DEFAULT_NUMBER_GENERATOR;
		}

		if (alphabet == null) {
			alphabet = DEFAULT_ALPHABET;
		}

		if (alphabet.length == 0 || alphabet.length >= 256) {
			throw new IllegalArgumentException("Alphabet must contain between 1 and 255 symbols.");
		}

		if (size <= 0) {
			throw new IllegalArgumentException("Size must be greater than zero.");
		}

		final int mask = (2 << (int) Math.floor(Math.log(alphabet.length - 1) / Math.log(2))) - 1;
		final int step = (int) Math.ceil(1.6 * mask * size / alphabet.length);

		final StringBuilder idBuilder = new StringBuilder();

		while (true) {
			final byte[] bytes = new byte[step];
			random.nextBytes(bytes);
			for (int i = 0; i < step; i++) {
				final int alphabetIndex = bytes[i] & mask;
				if (alphabetIndex < alphabet.length) {
					idBuilder.append(alphabet[alphabetIndex]);
					if (idBuilder.length() == size) {
						return idBuilder.toString();
					}
				}
			}
		}
	}
}
