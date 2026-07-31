package cn.luim.platform.api.uac.enums;

import cn.luim.boot.starter.base.enums.IBaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountType implements IBaseEnum<Integer> {

	MOBILE(1, "手机号"),
	EMAIL(2, "邮箱号")
	;

	@EnumValue
	private final Integer code;
	private final String message;
}
