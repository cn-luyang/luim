package cn.luim.boot.starter.base.utils.json;

import cn.luim.boot.starter.base.utils.constant.DatePattern;
import tools.jackson.core.json.PackageVersion;
import tools.jackson.databind.ext.javatime.deser.DurationDeserializer;
import tools.jackson.databind.ext.javatime.deser.InstantDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalDateDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalTimeDeserializer;
import tools.jackson.databind.ext.javatime.ser.DurationSerializer;
import tools.jackson.databind.ext.javatime.ser.InstantSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalTimeSerializer;
import tools.jackson.databind.module.SimpleModule;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author yang.lu
 */
public class JsonTimeModule extends SimpleModule {

	public JsonTimeModule() {
		super(PackageVersion.VERSION);

		// LocalDateTime 序列化，格式 yyyy-MM-dd HH:mm:ss
		this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DatePattern.NORM_DATETIME_FORMATTER));
		this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DatePattern.NORM_DATETIME_FORMATTER));

		// LocalDate 序列化，格式 yyyy-MM-dd
		this.addSerializer(LocalDate.class, new LocalDateSerializer(DatePattern.NORM_DATE_FORMATTER));
		this.addDeserializer(LocalDate.class, new LocalDateDeserializer(DatePattern.NORM_DATE_FORMATTER));

		// LocalTime 序列化，格式 HH:mm:ss
		this.addSerializer(LocalTime.class, new LocalTimeSerializer(DatePattern.NORM_TIME_FORMATTER));
		this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DatePattern.NORM_TIME_FORMATTER));

		// Instant
		this.addSerializer(Instant.class, InstantSerializer.INSTANCE);
		this.addDeserializer(Instant.class, InstantDeserializer.INSTANT);

		// Duration
		this.addSerializer(Duration.class, DurationSerializer.INSTANCE);
		this.addDeserializer(Duration.class, DurationDeserializer.INSTANCE);
	}
}
