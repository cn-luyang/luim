package cn.luim.boot.starter.base.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResultEnum implements IBaseEnum<String> {

	SUCCESS("0", "Success"),
	FAILURE("500", "Failure"),

	PARAM_MISSING("40001", "缺少必要参数"),
	PARAM_INVALID("40002", "参数格式不正确"),
	PARAM_OUT_OF_RANGE("40003", "参数超出允许范围"),
	PARAM_DUPLICATE("40004", "参数重复"),
	PARAM_TYPE_MISMATCH("40006", "参数类型不匹配"),

	DATA_NOT_FOUND("50001", "数据不存在"),
	DATA_ALREADY_EXISTS("409", "数据已存在"),
	DATA_CONFLICT("40901", "数据冲突"),
	DATA_NOT_MATCH("42201", "数据不匹配"),

	UNAUTHORIZED("401", "未登录或登录已过期"),
	FORBIDDEN("403", "权限不足，无权访问"),
	;

	private final String code;
	private final String message;
}
