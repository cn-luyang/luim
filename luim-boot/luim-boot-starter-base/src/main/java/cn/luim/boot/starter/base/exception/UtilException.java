package cn.luim.boot.starter.base.exception;

import cn.luim.boot.starter.base.utils.StringUtil;

/**
 * @author yang.lu
 */
public class UtilException extends RuntimeException {

	public UtilException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

	public UtilException(String message) {
		super(message);
	}

	public UtilException(String message, Throwable cause) {
		super(message, cause);
	}

	public UtilException(String message, Object... params) {
		super(StringUtil.format(message, params));
	}

	public UtilException(Throwable throwable, String message, Object... params) {
		super(StringUtil.format(message, params), throwable);
	}
}
