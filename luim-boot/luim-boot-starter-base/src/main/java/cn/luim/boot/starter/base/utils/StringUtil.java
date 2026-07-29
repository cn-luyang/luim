package cn.luim.boot.starter.base.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;

/**
 * @author yang.lu
 */
@UtilityClass
public class StringUtil{

	public static boolean isBlank(CharSequence cs) {
		return StringUtils.isBlank(cs);
	}

	public static boolean isNotBlank(CharSequence cs) {
		return StringUtils.isNotBlank(cs);
	}

	/**
	 * 如果字符串为空白则返回默认值，否则返回原字符串
	 *
	 * @param str        要检查的字符串
	 * @param defaultStr 默认值
	 * @return 处理后的字符串
	 * @author yang.lu
	 */
	public static String blankToDefault(CharSequence str, String defaultStr) {
		return isBlank(str) ? defaultStr : str.toString();
	}

	public static boolean startsWith(CharSequence str, CharSequence prefix) {
		return Strings.CS.startsWith(str, prefix);
	}

	public static String toStringOrNull(Object obj) {
		return null == obj ? null : obj.toString();
	}

	public static String substringAfter(String str, String find) {
		return StringUtils.substringAfter(str, find);
	}

	public static String format(CharSequence str, Object... params) {
		return null;
	}
}
