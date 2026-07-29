package cn.luim.boot.starter.base.utils;

import lombok.experimental.UtilityClass;

import java.util.Objects;

/**
 * @author yang.lu
 */
@UtilityClass
public class ObjectUtil {

	public static boolean isNull(Object obj) {
		return Objects.isNull(obj);
	}

	public static boolean notNull(Object obj) {
		return Objects.nonNull(obj);
	}

	public static boolean equals(Object obj1, Object obj2) {
		if (obj1 instanceof Number && obj2 instanceof Number) {
			return NumberUtil.equals((Number) obj1, (Number) obj2);
		}
		return Objects.equals(obj1, obj2);
	}
}
