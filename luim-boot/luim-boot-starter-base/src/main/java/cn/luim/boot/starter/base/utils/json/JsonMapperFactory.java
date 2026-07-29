package cn.luim.boot.starter.base.utils.json;

import lombok.experimental.UtilityClass;
import tools.jackson.core.json.JsonReadFeature;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

/**
 * @author yang.lu
 */
@UtilityClass
public class JsonMapperFactory {

	public static JsonMapper.Builder mapperBuilder(JsonMapper.Builder builder) {
		return builder
			// 允许单引号。如 {'name':'张三'}
			.enable(JsonReadFeature.ALLOW_SINGLE_QUOTES)
			// 允许未转义的控制字符。如 {"message": "Hello\nWorld"}
			.enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS)
			// 反序列化时，JSON 包含 Java 实体没有的字段，直接忽略。如 {"name":"张三", "age":25} Java 实体只有 name
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			// 序列化空对象 (空 POJO) 时不报错
			.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
			// 时间模块
			.addModule(new JsonTimeModule());
	}
}
