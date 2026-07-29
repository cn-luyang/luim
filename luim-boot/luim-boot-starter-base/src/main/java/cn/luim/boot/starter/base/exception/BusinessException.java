package cn.luim.boot.starter.base.exception;

import cn.luim.boot.starter.base.enums.IBaseEnum;
import cn.luim.boot.starter.base.enums.ResultEnum;
import cn.luim.boot.starter.base.utils.StringUtil;
import lombok.Getter;

import java.io.Serial;

/**
 * @author yang.lu
 */
@Getter
public class BusinessException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	private final String code;
	private final Object data;
	private final Object[] args;

	public BusinessException(String code, String message, Object[] args, Object data, Throwable cause) {
		super(StringUtil.format(message, args), cause);
		this.code = StringUtil.blankToDefault(code, ResultEnum.FAILURE.getCode());
		this.data = data;
		this.args = args;
	}

	public BusinessException(String message) {
		this(ResultEnum.FAILURE.getCode(), message, null, null, null);
	}

	public BusinessException(Throwable cause) {
		this(ResultEnum.FAILURE.getCode(), null, null, null, cause);
	}

	public BusinessException(String message, Throwable cause) {
		this(ResultEnum.FAILURE.getCode(), message, null, null, cause);
	}

	public BusinessException(String code, String message) {
		this(code, message, null, null, null);
	}

	public BusinessException(String code, String message, Throwable cause) {
		this(code, message, null, null, cause);
	}

	public BusinessException(IBaseEnum<?> baseEnum) {
		this(baseEnum, null);
	}

	public BusinessException(IBaseEnum<?> baseEnum, Throwable cause) {
		this(baseEnum != null ? StringUtil.toStringOrNull(baseEnum.getCode()) : null, baseEnum != null ? baseEnum.getMessage() : null, null, null, cause);
	}

	public static BusinessException.Builder builder() {
		return new BusinessException.Builder();
	}

	@Getter
	public static class Builder {
		private String code;
		private String message;
		private Object data;
		private Object[] args;
		private Throwable cause;

		public BusinessException.Builder code(String code) {
			this.code = StringUtil.blankToDefault(code, ResultEnum.FAILURE.getCode());
			return this;
		}

		public BusinessException.Builder message(String message) {
			this.message = StringUtil.blankToDefault(message, ResultEnum.FAILURE.getMessage());
			return this;
		}

		public BusinessException.Builder data(Object data) {
			this.data = data;
			return this;
		}

		public BusinessException.Builder args(Object... args) {
			this.args = args;
			return this;
		}

		public BusinessException.Builder cause(Throwable cause) {
			this.cause = cause;
			return this;
		}

		public BusinessException build() {
			return new BusinessException(code, message, args, data, cause);
		}
	}
}
