package cn.luim.support.idgen.common.enums;

import cn.luim.boot.starter.base.exception.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举类
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode implements ExceptionAssert<String> {

	BUSINESS_CODE_EXISTS("SEGMENT:CONFIG:001", "业务标识已存在"),



	;
	private final String code;
	private final String message;
}
