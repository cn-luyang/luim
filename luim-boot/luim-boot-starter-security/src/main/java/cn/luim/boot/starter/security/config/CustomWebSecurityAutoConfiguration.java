package cn.luim.boot.starter.security.config;

import cn.luim.boot.starter.security.authorize.AuthorizeRequestsCustomizer;
import cn.luim.boot.starter.security.filter.TokenAuthenticationFilter;
import cn.luim.boot.starter.security.handler.CustomAccessDeniedHandler;
import cn.luim.boot.starter.security.handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * Spring Security 过滤链配置
 *
 * @author yang.lu
 */
@AutoConfiguration
@RequiredArgsConstructor
@AutoConfigureAfter(CustomSecurityAutoConfiguration.class)
public class CustomWebSecurityAutoConfiguration {

	private final CustomAccessDeniedHandler customAccessDeniedHandler;
	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	private final TokenAuthenticationFilter tokenAuthenticationFilter;
	private final List<AuthorizeRequestsCustomizer> authorizeRequestsCustomizers;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		// 配置 HTTP 头部
		httpSecurity
			.headers(header ->
				// 禁用 X-Frame-Options 头，允许来自任何来源的 frame 嵌入
				header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
			// 禁用默认的表单登录
			.formLogin(AbstractHttpConfigurer::disable)
			// 禁用默认的登出功能
			.logout(AbstractHttpConfigurer::disable)
			// 禁用 HTTP Basic 认证
			.httpBasic(AbstractHttpConfigurer::disable)
			// 禁用CSRF(跨站请求伪造)保护,因为使用Token认证
			.csrf(AbstractHttpConfigurer::disable)
			// 配置跨域资源共享 (CORS) 允许所有来源、方法和头部
			.cors(Customizer.withDefaults())
			// 禁用 Session 管理 (使用 Token 进行认证，不需要 Session)
			.sessionManagement(AbstractHttpConfigurer::disable);

		// 配置异常处理
		httpSecurity
			.exceptionHandling(exceptionHandling ->
				exceptionHandling
					// 设置认证失败处理器（未登录时）
					.authenticationEntryPoint(customAuthenticationEntryPoint)
					// 设置授权失败处理器（权限不足时）
					.accessDeniedHandler(customAccessDeniedHandler)
			);

		// 配置请求授权规则
		httpSecurity.authorizeHttpRequests(registry -> {
			authorizeRequestsCustomizers.stream()
				.sorted(AnnotationAwareOrderComparator.INSTANCE)
				.forEach(customizer -> customizer.customize(registry));
			registry.anyRequest().authenticated();
		});

		httpSecurity.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}
}
