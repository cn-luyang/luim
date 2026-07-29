package cn.luim.boot.starter.security.utils;

import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.boot.starter.base.utils.constant.StringPool;
import cn.luim.boot.starter.security.context.UserContext;
import cn.luim.boot.starter.security.context.UserContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security 工具类
 *
 * @author yang.lu
 */
@UtilityClass
public class SecurityUtil {

	/**
	 * 从 HttpServletRequest 中提取 Bearer 和 Basic 两种认证方式 Token
	 *
	 * @param request HTTP 请求对象
	 * @return 提取出的 token 字符串，若无 token 则返回 null
	 */
	public static String extractToken(HttpServletRequest request) {

		String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (StringUtil.isNotBlank(token)) {
			if (StringUtil.startsWith(token, StringPool.BEARER_TOKEN)) {
				return StringUtil.substringAfter(token, StringPool.BEARER_TOKEN);
			}

			if (StringUtil.startsWith(token, StringPool.BASIC_TOKEN)) {
				return StringUtil.substringAfter(token, StringPool.BASIC_TOKEN);
			}
		}

		return token;
	}

	/**
	 * 获取当用户信息
	 *
	 * @return UserContext 对象，若未认证或类型不匹配则返回 null
	 */
	public static UserContext getUserContext() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (null != authentication) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserContext userContext) {
				return userContext;
			}
		}
		// 从 UserContextHolder 获取，避免 Dubbo 透传时下游服务没有 SecurityContext 情况
		return UserContextHolder.get();
	}
}
