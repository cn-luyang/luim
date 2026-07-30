package cn.luim.boot.starter.security.handler;

import cn.luim.boot.starter.base.enums.ResultEnum;
import cn.luim.boot.starter.base.model.Result;
import cn.luim.boot.starter.base.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * 认证失败处理器（未登录 / Token 无效）
 *
 * @author yang.lu
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private final static Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

	@Override
	public void commence(@NonNull HttpServletRequest request,
	                     @NonNull HttpServletResponse response,
	                     @NonNull AuthenticationException authException) {
		logger.error("认证失败拦截，请求路径: {}", request.getRequestURI(), authException);
		ServletUtil.writeJson(response, Result.failure(ResultEnum.UNAUTHORIZED));
	}
}
