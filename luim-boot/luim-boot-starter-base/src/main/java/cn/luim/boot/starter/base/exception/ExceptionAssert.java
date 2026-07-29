package cn.luim.boot.starter.base.exception;

import cn.luim.boot.starter.base.enums.IBaseEnum;
import cn.luim.boot.starter.base.utils.ObjectUtil;

/**
 * 业务异常断言接口
 *
 * @author yang.lu
 */
public interface ExceptionAssert<T> extends IBaseEnum<T> {

	default BusinessException newException() {
		return new BusinessException(this);
	}

	default BusinessException newException(Throwable cause) {
		return new BusinessException(this, cause);
	}

	/**
	 * 若为 false 则抛出异常
	 *
	 * @param condition 待判断的布尔表达式
	 * @author yang.lu
	 */
	default void isTrue(boolean condition) {
		if (!condition) {
			throw newException();
		}
	}

	/**
	 * 若为 true 则抛出异常
	 *
	 * @param condition 待判断的布尔表达式
	 * @author yang.lu
	 */
	default void isFalse(boolean condition) {
		if (condition) {
			throw newException();
		}
	}

	/**
	 * 若不为 null 则抛出异常
	 *
	 * @param obj 被检查的对象
	 * @author yang.lu
	 */
	default void isNull(Object obj) {
		if (ObjectUtil.notNull(obj)) {
			throw newException();
		}
	}

	/**
	 * 若为 null 则抛出异常
	 *
	 * @param obj 被检查的对象
	 * @author yang.lu
	 */
	default void notNull(Object obj) {
		if (ObjectUtil.isNull(obj)) {
			throw newException();
		}
	}
}
