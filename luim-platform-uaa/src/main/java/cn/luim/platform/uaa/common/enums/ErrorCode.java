package cn.luim.platform.uaa.common.enums;

import cn.luim.boot.starter.base.exception.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode implements ExceptionAssert<String> {

	LOGIN_BAD_CREDENTIALS("UAA:AUTH:001", "账号或密码错误"),

	// 客户端相关
	CLIENT_EXISTS("UAA:CLIENT:001", "客户端已存在"),
	CLIENT_NOT_FOUND("UAA:CLIENT:002", "客户端不存在或已被删除");

	private final String code;
	private final String message;
}
