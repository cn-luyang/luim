package cn.luim.platform.api.uac;

import cn.luim.boot.starter.base.model.Result;
import cn.luim.platform.api.uac.model.response.GetUserInfoResponse;

public interface UserRpcService {

	Result<GetUserInfoResponse> getUserInfo(String userId);
}
