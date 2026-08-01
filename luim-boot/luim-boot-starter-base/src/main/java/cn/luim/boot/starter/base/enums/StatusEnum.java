package cn.luim.boot.starter.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 公用状态枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StatusEnum implements IBaseEnum<Integer> {

	UNKNOWN(0, "未知"),
	ENABLED(1, "启用"),
	DISABLED(2, "禁用");

	@EnumValue
	private final Integer code;
	private final String message;
}
