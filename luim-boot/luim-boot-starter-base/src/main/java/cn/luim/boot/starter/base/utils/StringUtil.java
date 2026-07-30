package cn.luim.boot.starter.base.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;

/**
 * 字符串工具类
 *
 * @author yang.lu
 */
@UtilityClass
public class StringUtil {

	/**
	 * 检查字符序列是否为空白字符（null、空串或仅含空白符）
	 *
	 * @param str 待检查的字符序列
	 * @return 若为空白字符则返回 true
	 */
	public static boolean isBlank(CharSequence str) {
		return StringUtils.isBlank(str);
	}

	/**
	 * 检查字符序列是否非空白字符
	 *
	 * @param str 待检查的字符序列
	 * @return 若非空白字符则返回 true
	 */
	public static boolean isNotBlank(CharSequence str) {
		return StringUtils.isNotBlank(str);
	}

	/**
	 * 检查多个字符序列中是否存在任意一个空白字符
	 *
	 * @param strs 待检查的字符序列数组
	 * @return 存在空白字符则返回 true
	 */
	public static boolean hasBlank(CharSequence... strs) {
		if (ArrayUtil.isEmpty(strs)) {
			return true;
		}

		for (CharSequence str : strs) {
			if (isBlank(str)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 若字符序列为空白，则返回默认字符串
	 *
	 * @param str        待检测的字符序列
	 * @param defaultStr 默认字符串
	 * @return 目标字符串或默认字符串
	 */
	public static String blankToDefault(CharSequence str, String defaultStr) {
		return isBlank(str) ? defaultStr : str.toString();
	}

	/**
	 * 将对象转换为字符串，对象为 null 时返回 null
	 *
	 * @param object 待转换的对象
	 * @return 对象的字符串表示形式或 null
	 */
	public static String toStringOrNull(Object object) {
		return null == object ? null : object.toString();
	}

	/**
	 * 检查字符序列是否以指定前缀开头
	 *
	 * @param str    原字符序列
	 * @param prefix 前缀字符序列
	 * @return 若匹配前缀则返回 true
	 */
	public static boolean startsWith(CharSequence str, CharSequence prefix) {
		return Strings.CS.startsWith(str, prefix);
	}

	/**
	 * 截取指定字符串首次出现之后的子串
	 *
	 * @param str  原字符串
	 * @param find 匹配字符串
	 * @return 截取后的子串
	 */
	public static String substringAfter(String str, String find) {
		return StringUtils.substringAfter(str, find);
	}

	/**
	 * 格式化文本
	 *
	 * @param str    包含占位符的字符序列
	 * @param params 格式化参数
	 * @return 格式化后的字符串
	 */
	public static String format(CharSequence str, Object... params) {
		return null;
	}
}
