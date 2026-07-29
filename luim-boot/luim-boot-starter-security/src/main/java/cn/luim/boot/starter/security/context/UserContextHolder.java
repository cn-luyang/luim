package cn.luim.boot.starter.security.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 用户上下文持有器
 *
 * @author yang.lu
 */
public class UserContextHolder {

	private static final ThreadLocal<UserContext> CONTEXT = new TransmittableThreadLocal<>();

	private UserContextHolder() {
	}

	public static void set(UserContext ctx) {
		if (null != ctx) {
			CONTEXT.set(ctx);
		}
	}

	public static UserContext get() {
		return CONTEXT.get();
	}

	public static void remove() {
		CONTEXT.remove();
	}

	public static boolean isPresent() {
		return null != CONTEXT.get();
	}
}
