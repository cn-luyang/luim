package cn.luim.boot.starter.base.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类
 *
 * @author yang.lu
 */
@UtilityClass
public class ArrayUtil {

	/**
	 * 检查数组是否为空
	 *
	 * @param array 待检查的数组
	 * @param <T>   数组元素类型
	 * @return 若数组为 null 或长度为 0 则返回 true
	 */
	public static <T> boolean isEmpty(final T[] array) {
		return ArrayUtils.isEmpty(array);
	}
}
