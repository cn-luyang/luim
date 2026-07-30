package cn.luim.platform.uaa.service.impl;

import cn.luim.boot.starter.base.model.Result;
import cn.luim.boot.starter.base.utils.id.IdUtil;
import cn.luim.boot.starter.base.utils.json.JsonUtil;
import cn.luim.platform.api.uac.AccountRpcService;
import cn.luim.platform.api.uac.UserRpcService;
import cn.luim.platform.api.uac.model.request.AccountAuthRequest;
import cn.luim.platform.api.uac.model.response.GetUserInfoResponse;
import cn.luim.platform.uaa.beans.command.CreateUserTokenCommand;
import cn.luim.platform.uaa.beans.command.LoginCommand;
import cn.luim.platform.uaa.beans.convert.AuthConvert;
import cn.luim.platform.uaa.beans.dto.ClientDetailDTO;
import cn.luim.platform.uaa.beans.dto.CreateUserTokenDTO;
import cn.luim.platform.uaa.common.enums.ErrorCode;
import cn.luim.platform.uaa.service.AuthService;
import cn.luim.platform.uaa.service.ClientService;
import cn.luim.platform.uaa.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	private final ClientService clientService;
	private final TokenService tokenService;
	private final AuthConvert authConvert;

	@DubboReference
	private AccountRpcService accountRpcService;
	@DubboReference
	private UserRpcService userRpcService;

	@Override
	public void login(LoginCommand command) {

		// 获取客户端信息
		ClientDetailDTO clientDetailDTO = clientService.getDetail(command.getClientId());
		ErrorCode.CLIENT_NOT_FOUND.notNull(clientDetailDTO);

		// 校验账号密码
		AccountAuthRequest accountAuthRequest = authConvert.buildAccountAuthRequest(command);
		Result<String> validateAccountResult = accountRpcService.validateAccount(accountAuthRequest);
		ErrorCode.LOGIN_BAD_CREDENTIALS.isTrue(validateAccountResult.isSuccess());

		// 获取用户信息
		Result<GetUserInfoResponse> userInfoResult = userRpcService.getUserInfo(validateAccountResult.getData());
		ErrorCode.LOGIN_BAD_CREDENTIALS.isTrue(userInfoResult.isSuccess());

		// 创建用户 Token
		CreateUserTokenCommand createUserTokenCommand = authConvert.buildCreateUserTokenCommand(clientDetailDTO, userInfoResult.getData());
		CreateUserTokenDTO userToken = tokenService.createUserToken(createUserTokenCommand);
		logger.info("用户Token:{}", JsonUtil.toJsonString(userInfoResult));
	}

	public static void main(String[] args) {
		System.out.println(IdUtil.simpleUUID());
	}
}
