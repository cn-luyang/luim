package cn.luim.boot.starter.base.utils;

import lombok.experimental.UtilityClass;

import java.util.Objects;

/**
 * @author yang.lu
 */
@UtilityClass
public class ObjectUtil {

	public static boolean equals(Object obj1, Object obj2) {
		return equal(obj1, obj2);
	}

	public static boolean equal(Object obj1, Object obj2) {
		if (obj1 instanceof Number && obj2 instanceof Number) {
			return NumberUtil.equals((Number) obj1, (Number) obj2);
		}
		return Objects.equals(obj1, obj2);
	}
}
