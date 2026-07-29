package cn.luim.boot.starter.security.handler;

import cn.luim.boot.starter.base.enums.ResultEnum;
import cn.luim.boot.starter.base.model.Result;
import cn.luim.boot.starter.base.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 授权失败处理器（已登录，但权限不足）
 *
 * @author yang.lu
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(@NonNull HttpServletRequest request,
	                   @NonNull HttpServletResponse response,
	                   @NonNull AccessDeniedException accessDeniedException) {

		ServletUtil.writeJson(response, Result.failure(ResultEnum.FORBIDDEN));
	}
}
