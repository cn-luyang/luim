package cn.luim.boot.starter.security.authorize;

import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * 自定义 HTTP 权限配置回调接口
 * 业务服务可自定义匹配规则
 *
 * @Configuration
 * public class UaaServiceSecurityConfig {
 *
 * 	    @Bean
 *    public AuthorizeRequestsCustomizer userServiceAuthorizeCustomizer() {
 * 		return registry -> registry
 * 			.requestMatchers("/testThree").permitAll() ;
 *    }
 * }
 *
 * @author yang.lu
 */
@FunctionalInterface
public interface AuthorizeRequestsCustomizer extends Ordered {

	/**
	 * 自定义权限匹配规则
	 */
	void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry);

	@Override
	default int getOrder() {
		return 0;
	}
}
