package cn.luim.boot.starter.base.utils;

import lombok.experimental.UtilityClass;

/**
 * @author yang.lu
 */
@UtilityClass
public class StrUtil {

	public static boolean isBlank(CharSequence cs) {
		int length;
		if (cs == null || (length = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(CharSequence cs) {
		return !isBlank(cs);
	}
}
