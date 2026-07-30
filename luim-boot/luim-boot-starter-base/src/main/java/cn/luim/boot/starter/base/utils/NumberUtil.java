package cn.luim.boot.starter.base.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 数字工具类
 *
 * @author yang.lu
 */
@UtilityClass
public class NumberUtil {

	/**
	 * 比较两个 Number 对象数值是否相等
	 *
	 * @param num1 第一个数值
	 * @param num2 第二个数值
	 * @return 数值相等返回 true
	 */
	public static boolean equals(Number num1, Number num2) {
		if (Objects.equals(num1, num2)) {
			return true;
		}

		if (num1 == null || num2 == null) {
			return false;
		}

		if (num1 instanceof BigDecimal && num2 instanceof BigDecimal) {
			return equals((BigDecimal) num1, (BigDecimal) num2);
		}

		return num1.equals(num2);
	}

	/**
	 * 比较两个 BigDecimal 数值是否相等（忽略精度差异）
	 *
	 * @param num1 第一个 BigDecimal 数值
	 * @param num2 第二个 BigDecimal 数值
	 * @return 数值相等返回 true
	 */
	public static boolean equals(BigDecimal num1, BigDecimal num2) {
		if (Objects.equals(num1, num2)) {
			return true;
		}

		if (num1 == null || num2 == null) {
			return false;
		}

		return num1.compareTo(num2) == 0;
	}
}
