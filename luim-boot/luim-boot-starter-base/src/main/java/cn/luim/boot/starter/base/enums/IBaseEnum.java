package cn.luim.boot.starter.base.enums;

import cn.luim.boot.starter.base.utils.ObjectUtil;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 通用枚举接口
 *
 * @author yang.lu
 */
public interface IBaseEnum<T> extends Serializable {

	T getCode();

	String getMessage();

	default boolean equals(String code) {
		return ObjectUtil.equals(this.getCode(), code);
	}

	/**
	 * 根据code获取枚举
	 *
	 * <pre class="code">
	 *     ResultEnum SUCCESS_ENUM = IBaseEnum.getByCode(ResultEnum.class, 200);
	 * </pre>
	 *
	 * @param clazz 待匹配枚举类
	 * @param code  待匹配枚举的Code
	 * @return T 匹配的枚举，未找到返回null
	 */
	static <T, E extends IBaseEnum<T>> E getByCode(Class<E> clazz, T code) {
		return Arrays.stream(clazz.getEnumConstants())
			.filter(v -> ObjectUtil.equals(v.getCode(), code))
			.findFirst()
			.orElse(null);
	}
}
