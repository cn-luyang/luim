package cn.luim.boot.starter.security.filter;

import cn.luim.boot.starter.base.utils.json.JsonUtil;
import cn.luim.boot.starter.security.context.UserContext;
import cn.luim.boot.starter.security.context.UserContextHolder;
import cn.luim.boot.starter.security.utils.SecurityUtil;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

/**
 * Dubbo 出站时将 SecurityContextHolder 中的用户信息透传到下游
 *
 * @author yang.lu
 */
@Activate(group = {CommonConstants.CONSUMER}, order = -10000)
public class UserContextConsumerFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

		UserContext userContext = SecurityUtil.getUserContext();
		if (null == userContext) {
			userContext = UserContextHolder.get();
		}

		if (null != userContext && userContext.isValid()) {
			invocation.setAttachment(AttachmentKeys.USER_CONTEXT, JsonUtil.toJsonString(userContext));
		}

		return invoker.invoke(invocation);
	}
}
