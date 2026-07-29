package cn.luim.boot.starter.base.utils.constant;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author yang.lu
 */
public class DatePattern {

	/**
	 * 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss
	 */
	public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 标准日期时间格式化器：yyyy-MM-dd HH:mm:ss
	 */
	public static final DateTimeFormatter NORM_DATETIME_FORMATTER = createFormatter(NORM_DATETIME_PATTERN);

	/**
	 * 标准日期格式：yyyy-MM-dd
	 */
	public static final String NORM_DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * 标准日期格式化器：yyyy-MM-dd
	 */
	public static final DateTimeFormatter NORM_DATE_FORMATTER = createFormatter(NORM_DATE_PATTERN);

	/**
	 * 标准时间格式：HH:mm:ss
	 */
	public static final String NORM_TIME_PATTERN = "HH:mm:ss";

	/**
	 * 标准时间格式化器：HH:mm:ss
	 */
	public static final DateTimeFormatter NORM_TIME_FORMATTER = createFormatter(NORM_TIME_PATTERN);

	public static DateTimeFormatter createFormatter(String pattern) {
		return DateTimeFormatter.ofPattern(pattern, Locale.getDefault());
	}
}
