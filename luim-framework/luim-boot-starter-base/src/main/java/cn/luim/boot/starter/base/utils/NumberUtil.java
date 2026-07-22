package cn.luim.boot.starter.base.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author yang.lu
 */
@UtilityClass
public class NumberUtil {

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
