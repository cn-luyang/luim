package cn.luim.boot.starter.base.utils.json;

import cn.luim.boot.starter.base.utils.ObjectUtil;
import cn.luim.boot.starter.base.utils.StringUtil;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.util.Map;

/**
 * Json 工具类
 *
 * @author yang.lu
 */
@UtilityClass
public class JsonUtil {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static final ObjectMapper OBJECT_MAPPER;

	static {
		OBJECT_MAPPER = JsonMapperFactory.mapperBuilder(JsonMapper.builder()).build();
	}

	public static ObjectMapper getObjectMapper() {
		return OBJECT_MAPPER;
	}

	/**
	 * 对象转 JSON 字符串
	 *
	 * @param obj 待序列化对象
	 * @return JSON 字符串，失败或 obj 为 null 时返回 null
	 */
	public static <T> String toJsonString(T obj) {

		if (null == obj) {
			return null;
		}

		try {
			return getObjectMapper().writeValueAsString(obj);
		} catch (JacksonException e) {
			logger.error("JSON serialization failed", e);
			return null;
		}
	}

	public static <T> T parseObject(String text, Class<T> clazz) {
		if (StringUtil.isBlank(text) || ObjectUtil.isNull(clazz)) {
			return null;
		}
		try {
			return getObjectMapper().readValue(text, clazz);
		} catch (Exception e) {
			logger.error("JSON object conversion exception", e);
			return null;
		}
	}

	public static <K, V> Map<K, V> parseMap(String json, Class<K> keyClass, Class<V> valueClass) {
		JavaType javaType = getObjectMapper().getTypeFactory().constructMapType(Map.class, keyClass, valueClass);
		return parseMap(json, javaType);
	}

	public static <K, V> Map<K, V> parseMap(String json, JavaType javaType) {
		return getObjectMapper().readValue(json, javaType);
	}

	public static <K, V> Map<K, V> toMap(Object obj, Class<K> keyClass, Class<V> valueClass) {
		JavaType javaType = getObjectMapper().getTypeFactory().constructMapType(Map.class, keyClass, valueClass);
		return toMap(obj, javaType);
	}

	public static <K, V> Map<K, V> toMap(Object obj, JavaType javaType) {
		return getObjectMapper().convertValue(obj, javaType);
	}
}
