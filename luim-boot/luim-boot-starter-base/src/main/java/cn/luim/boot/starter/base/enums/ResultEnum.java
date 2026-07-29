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

	UNAUTHORIZED("401", "未登录或登录已过期"),
	FORBIDDEN("403", "权限不足，无权访问"),
	;

	private final String code;
	private final String message;
}
