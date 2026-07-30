package cn.luim.platform.api.uac;

import cn.luim.boot.starter.base.model.Result;
import cn.luim.platform.api.uac.model.request.AccountAuthRequest;

public interface AccountRpcService {

	/**
	 * 校验账号与密码，校验通过返回 userId
	 *
	 * @param request 认证请求参数
	 * @return Result<String> 用户ID
	 */
	Result<String> validateAccount(AccountAuthRequest request);
}
