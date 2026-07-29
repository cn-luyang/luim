package cn.luim.boot.starter.security.filter;

import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.boot.starter.security.context.UserContext;
import cn.luim.boot.starter.security.dubbo.AuthRpcService;
import cn.luim.boot.starter.security.utils.SecurityUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * Token 认证过滤器
 *
 * @author yang.lu
 */
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

	private final AuthRpcService authRpcService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,
	                                @NonNull HttpServletResponse response,
	                                @NonNull FilterChain filterChain) throws ServletException, IOException {

		// 提取并校验Token存在性
		String token = SecurityUtil.extractToken(request);
		if (StringUtil.isBlank(token)) {
			logger.debug("No token found in request");
			filterChain.doFilter(request, response);
			return;
		}

		// 远程验证Token有效性，获取用户上下文
		UserContext userContext = authRpcService.validateToken(token);
		if (null == userContext) {
			logger.warn("Token validation failed for token:{}", token);
			filterChain.doFilter(request, response);
			return;
		}

		// 构建认证凭证并注入Spring Security上下文
		var authenticationToken = new UsernamePasswordAuthenticationToken(userContext, null, Collections.emptyList());
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);

		filterChain.doFilter(request, response);
	}
}
