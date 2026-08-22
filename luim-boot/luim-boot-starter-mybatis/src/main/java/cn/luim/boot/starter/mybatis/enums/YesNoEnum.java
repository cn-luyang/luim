package cn.luim.boot.starter.mybatis.enums;

import cn.luim.boot.starter.base.enums.IBaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum YesNoEnum implements IBaseEnum<Integer> {

	NO(0, "否"),
	YES(1, "是");

	@EnumValue
	private final Integer code;
	private final String message;
}
