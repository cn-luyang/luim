package cn.luim.boot.starter.dubbo.handler;

import cn.luim.boot.starter.base.model.Result;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Dubbo 全局异常
 * @author yang.lu
 */
@RestControllerAdvice
public class DubboExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(DubboExceptionHandler.class);

	@ExceptionHandler(RpcException.class)
	public Result<Void> handleDubboException(RpcException e) {
		logger.error("RPC异常: code={}, message={}", e.getCode(), e.getMessage());
		return Result.failure("RPC异常，请联系管理员确认");
	}
}
