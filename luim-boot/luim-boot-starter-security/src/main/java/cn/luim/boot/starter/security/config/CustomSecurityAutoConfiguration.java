package cn.luim.boot.starter.security.config;

import cn.luim.boot.starter.security.authorize.DefaultAuthorizeRequestsCustomizer;
import cn.luim.boot.starter.security.context.CustomSecurityContextHolderStrategy;
import cn.luim.boot.starter.security.dubbo.AuthRpcService;
import cn.luim.boot.starter.security.filter.TokenAuthenticationFilter;
import cn.luim.boot.starter.security.handler.CustomAccessDeniedHandler;
import cn.luim.boot.starter.security.handler.CustomAuthenticationEntryPoint;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 自动配置
 */
@AutoConfiguration
public class CustomSecurityAutoConfiguration {

	@DubboReference(version = "1.0.0")
	private AuthRpcService authRpcService;

	/**
	 * BCrypt 密码编码器
	 *
	 * @return BCrypt 密码编码器实例
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 令牌认证过滤器
	 *
	 * @return 令牌认证过滤器实例
	 */
	@Bean
	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter(authRpcService);
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}

	@Bean
	public DefaultAuthorizeRequestsCustomizer defaultAuthorizeRequestsCustomizer(ApplicationContext applicationContext) {
		return new DefaultAuthorizeRequestsCustomizer(applicationContext);
	}

	/**
	 * Security Context 存储策略
	 *
	 * @return TTL 安全上下文持有策略实例
	 */
	@Bean
	public static SecurityContextHolderStrategy securityContextHolderStrategy() {
		CustomSecurityContextHolderStrategy strategy = new CustomSecurityContextHolderStrategy();
		SecurityContextHolder.setContextHolderStrategy(strategy);
		return strategy;
	}
}
