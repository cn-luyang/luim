package cn.luim.platform.uac.common.enums.database;

import cn.luim.boot.starter.base.enums.IBaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
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

	@EnumValue
	private final Integer code;
	private final String message;

	public static boolean isEmployee(Integer code) {
		return EMPLOYEE.code.equals(code);
	}
}
