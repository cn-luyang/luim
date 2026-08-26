package cn.luim.boot.starter.security.filter;

import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.boot.starter.base.utils.json.JsonUtil;
import cn.luim.boot.starter.security.context.UserContext;
import cn.luim.boot.starter.security.context.UserContextHolder;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

/**
 * Dubbo 入站时提取上游透传的 UserContext 并存入线程上下文
 *
 * @author yang.lu
 */
@Activate(group = {CommonConstants.PROVIDER}, order = -10000)
public class UserContextProviderFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

		try {
			// 从 Dubbo ServerAttachment 获取上游透传的用户信息
			String userContextJson = invocation.getAttachment(AttachmentKeys.USER_CONTEXT);
			if (StringUtil.isNotBlank(userContextJson)) {
				UserContext userContext = JsonUtil.parseObject(userContextJson, UserContext.class);
				if (null != userContext && userContext.isValid()) {
					UserContextHolder.set(userContext);
				}
			}

			return invoker.invoke(invocation);
		} finally {
			UserContextHolder.remove();
		}
	}
}
