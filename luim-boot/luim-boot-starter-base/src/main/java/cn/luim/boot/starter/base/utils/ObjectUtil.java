package cn.luim.boot.starter.base.utils;

import lombok.experimental.UtilityClass;

import java.util.Objects;

/**
 * 对象工具类
 *
 * @author yang.lu
 */
@UtilityClass
public class ObjectUtil {

	/**
	 * 判断对象是否为 null
	 *
	 * @param object 待检查的对象
	 * @return 若对象为 null 则返回 true
	 */
	public static boolean isNull(Object object) {
		return Objects.isNull(object);
	}

	/**
	 * 判断对象是否不为 null
	 *
	 * @param object 待检查的对象
	 * @return 若对象不为 null 则返回 true
	 */
	public static boolean notNull(Object object) {
		return Objects.nonNull(object);
	}

	/**
	 * 比较两个对象是否相等（支持数值类型的特殊比较）
	 *
	 * @param object1 第一个对象
	 * @param object2 第二个对象
	 * @return 相等返回 true
	 */
	public static boolean equals(Object object1, Object object2) {
		if (object1 instanceof Number && object2 instanceof Number) {
			return NumberUtil.equals((Number) object1, (Number) object2);
		}
		return Objects.equals(object1, object2);
	}
}
