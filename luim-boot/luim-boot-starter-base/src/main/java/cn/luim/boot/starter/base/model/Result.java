package cn.luim.boot.starter.base.model;

import cn.luim.boot.starter.base.enums.IBaseEnum;
import cn.luim.boot.starter.base.enums.ResultEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yang.lu
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> implements Serializable {

	@Serial
	private static final long serialVersionUID = -7703727300828281922L;

	private String code;
	private String message;
	private boolean success;
	private T data;

	private Result(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.success = ResultEnum.SUCCESS.equals(code);
		this.data = data;
	}

	public static <T> Result<T> success() {
		return success(null);
	}

	public static <T> Result<T> success(T data) {
		return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
	}

	public static <T> Result<T> failure(String message) {
		return failure(ResultEnum.FAILURE.getCode(), message);
	}

	public static <T> Result<T> failure(String code, String message) {
		return new Result<>(code, message, null);
	}

	public static <T> Result<T> failure(String code, String message, T data) {
		return new Result<>(code, message, data);
	}

	public static <T> Result<T> failure(IBaseEnum<String> baseEnum) {
		return new Result<>(baseEnum.getCode(), baseEnum.getMessage(), null);
	}
}
