package cn.luim.boot.starter.base.enums;

import cn.luim.boot.starter.base.utils.ObjectUtil;

import java.io.Serializable;

/**
 * @author yang.lu
 */
public interface IBaseEnum<T> extends Serializable {

	T getCode();

	String getMessage();

	default boolean equals(String code) {
		return ObjectUtil.equals(this.getCode(), code);
	}
}
