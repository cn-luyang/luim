package cn.luim.boot.starter.security.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.context.SecurityContextImpl;

import java.util.function.Supplier;

/**
 * 自定义使用 TransmittableThreadLocal 替换 Spring Security 默认线程上下文，以支持线程池复用场景下的用户信息传递
 *
 * @author yang.lu
 */
public class CustomSecurityContextHolderStrategy implements SecurityContextHolderStrategy {

	private static final ThreadLocal<SecurityContext> CONTEXT_HOLDER = new TransmittableThreadLocal<>();

	@Override
	public void clearContext() {
		CONTEXT_HOLDER.remove();
	}

	@Override
	public @NonNull SecurityContext getContext() {
		SecurityContext securityContext = CONTEXT_HOLDER.get();
		if (null == securityContext) {
			securityContext = createEmptyContext();
			CONTEXT_HOLDER.set(securityContext);
		}

		return securityContext;
	}

	@Override
	public void setContext(@NonNull SecurityContext context) {
		CONTEXT_HOLDER.set(context);
	}

	@Override
	public @NonNull SecurityContext createEmptyContext() {
		return new SecurityContextImpl();
	}

	@Override
	public @NonNull Supplier<SecurityContext> getDeferredContext() {
		return this::getContext;
	}

	@Override
	public void setDeferredContext(Supplier<SecurityContext> deferredContext) {
		CONTEXT_HOLDER.set(deferredContext.get());
	}
}
