package cn.luim.boot.starter.base.utils;

import cn.luim.boot.starter.base.exception.UtilException;
import cn.luim.boot.starter.base.utils.json.JsonUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Servlet 工具类
 *
 * @author yang.lu
 */
@UtilityClass
public class ServletUtil {

	public static void writeJson(HttpServletResponse response, Object object) {
		String content = JsonUtil.toJsonString(object);
		write(response, content, "application/json");
	}

	public static void write(HttpServletResponse response, String text, String contentType) {
		response.setContentType(contentType);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		try (var writer = response.getWriter()) {
			writer.write(text);
			writer.flush();
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}
}
