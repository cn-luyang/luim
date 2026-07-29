package cn.luim.boot.starter.web.config;

import cn.luim.boot.starter.base.utils.json.JsonMapperFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * Jackson 自动配置
 *
 * @author yang.lu
 */
@AutoConfiguration
public class CustomJacksonAutoConfiguration {

	@Bean
	public JsonMapperBuilderCustomizer jsonMapperBuilderCustomizer() {
		return JsonMapperFactory::mapperBuilder;
	}
}
