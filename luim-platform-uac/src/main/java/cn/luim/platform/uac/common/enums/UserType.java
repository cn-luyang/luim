package cn.luim.platform.uac.common.enums;

import cn.luim.boot.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型枚举类
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserType implements IBaseEnum<Integer> {

	EMPLOYEE(1, "员工");

	private final Integer code;
	private final String message;

	public boolean isEmployee() {
		return this == EMPLOYEE;
	}
}
