package cn.luim.platform.uac.common.enums;

import cn.luim.boot.starter.base.exception.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode implements ExceptionAssert<String> {

	// 部门相关
	DEPT_ROOT_EXISTS("UAC:DEPT:001", "根节点已存在"),
	DEPT_ROOT_NOT_MODIFY("UAC:DEPT:001", "根节禁止修改"),
	DEPT_PARENT_NOT_FOUND("UAC:DEPT:001", "父部门不存在"),
	DEPT_PARENT_DISABLED("UAC:DEPT:002", "父部门已禁用"),
	DEPT_PARENT_IS_SELF("UAC:DEPT:005", "不能将自己设为父部门"),
	DEPT_PARENT_IS_CHILD("UAC:DEPT:006", "父部门不能是其子部门"),
	DEPT_EXCEEDS_DEPTH("UAC:DEPT:003", "部门层级超限"),
	DEPT_NOT_FOUND("UAC:DEPT:001", "部门不存在"),
	DEPT_NAME_DUPLICATE("UAC:DEPT:004", "部门名称重复"),
	// 登录认证相关
	LOGIN_BAD_CREDENTIALS("UAA:AUTH:001", "账号或密码错误"),

	// 客户端相关
	CLIENT_EXISTS("UAA:CLIENT:001", "客户端已存在"),
	CLIENT_NOT_FOUND("UAA:CLIENT:002", "客户端不存在或已被删除");

	private final String code;
	private final String message;
}
