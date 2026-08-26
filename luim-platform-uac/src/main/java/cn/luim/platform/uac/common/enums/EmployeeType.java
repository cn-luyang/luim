package cn.luim.platform.uac.common.enums;

import cn.luim.boot.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 员工类型枚举类
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EmployeeType implements IBaseEnum<Integer> {

	REGULAR(1, "正式员工"),
	INTERN(2, "实习生"),
	OUTSOURCED(3, "外协人员"),
	;

	private final Integer code;
	private final String message;
}
