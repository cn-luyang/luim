package cn.luim.boot.starter.security.authorize;

import cn.luim.boot.starter.security.annotation.Anonymous;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;

/**
 * @author yang.lu
 */
public class DefaultAuthorizeRequestsCustomizer implements AuthorizeRequestsCustomizer {

	private final ApplicationContext applicationContext;

	public DefaultAuthorizeRequestsCustomizer(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
		// 静态资源与通用端点放行
		registry.requestMatchers(HttpMethod.GET, "/*.html", "/*.css", "/*.js").permitAll()
			.requestMatchers(
				"/v3/api-docs/**",
				"/webjars/**",
				"/swagger-ui",
				"/swagger-ui/**",
				"/swagger-resources/**",
				"/doc.html",
				"/actuator", "/actuator/**",
				"/druid/**"
			).permitAll();

		// 扫描并放行标注 @Anonymous 的接口
		scanAnonymousUrls(registry);
	}

	/**
	 * 扫描所有标注 @Anonymous 的接口自动放行
	 */
	private void scanAnonymousUrls(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
		// 获取所有 RequestMappingHandlerMapping
		Map<String, RequestMappingHandlerMapping> handlerMappings = applicationContext.getBeansOfType(RequestMappingHandlerMapping.class);
		if (handlerMappings.isEmpty()) {
			return;
		}

		for (RequestMappingHandlerMapping mapping : handlerMappings.values()) {
			Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
			if (handlerMethods.isEmpty()) {
				continue;
			}

			handlerMethods.forEach((requestMappingInfo, handlerMethod) -> {
				// 检查类或方法上是否标注 @Anonymous 注解
				if (!handlerMethod.hasMethodAnnotation(Anonymous.class)
					&& !AnnotatedElementUtils.hasAnnotation(handlerMethod.getBeanType(), Anonymous.class)) {
					return;
				}

				// 提取请求路径
				PathPatternsRequestCondition pathPatternsCondition = requestMappingInfo.getPathPatternsCondition();
				if (null != pathPatternsCondition) {
					Set<String> patternValues = pathPatternsCondition.getPatternValues();
					if (!patternValues.isEmpty()) {
						String[] patternArray = patternValues.toArray(String[]::new);
						RequestMethodsRequestCondition methodsCond = requestMappingInfo.getMethodsCondition();
						// 根据是否指定HTTP方法，分别配置放行策略
						if (methodsCond.getMethods().isEmpty()) {
							registry.requestMatchers(patternArray).permitAll();
						} else {
							// 指定了方法：仅放行指定的HTTP方法
							methodsCond.getMethods().forEach(method ->
								registry.requestMatchers(HttpMethod.valueOf(method.name()), patternArray).permitAll()
							);
						}
					}
				}
			});
		}
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE + 100;
	}
}
