package cn.luim.boot.starter.web.handler;

import cn.luim.boot.starter.base.exception.BusinessException;
import cn.luim.boot.starter.base.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yang.lu
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(BusinessException.class)
	public Result<?> handleBaseException(BusinessException e) {
		logger.error("业务异常: code={}, message={}", e.getCode(), e.getMessage());
		return Result.failure(e.getMessage());
	}
}
