package cn.luim.boot.starter.mybatis.enums;

import cn.luim.boot.starter.base.enums.IBaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 布尔枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BooleanEnum implements IBaseEnum<Integer> {

	FALSE(0, "假"),
	TRUE(1, "真");

	@EnumValue
	private final Integer code;
	private final String message;
}
