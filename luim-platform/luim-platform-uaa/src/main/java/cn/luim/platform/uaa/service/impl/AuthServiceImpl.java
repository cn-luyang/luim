package cn.luim.platform.uaa.service.impl;

import cn.luim.boot.starter.base.model.Result;
import cn.luim.platform.api.uac.AccountRpcService;
import cn.luim.platform.api.uac.UserRpcService;
import cn.luim.platform.api.uac.model.request.AccountAuthRequest;
import cn.luim.platform.api.uac.model.response.GetUserInfoResponse;
import cn.luim.platform.uaa.common.enums.ErrorCode;
import cn.luim.platform.uaa.model.command.CreateUserTokenCommand;
import cn.luim.platform.uaa.model.command.UserLoginCommand;
import cn.luim.platform.uaa.model.convert.AuthConvert;
import cn.luim.platform.uaa.model.dto.ClientDetailDTO;
import cn.luim.platform.uaa.model.dto.CreateUserTokenDTO;
import cn.luim.platform.uaa.model.dto.UserLoginDTO;
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
	public UserLoginDTO login(UserLoginCommand command) {

		// 获取客户端信息
		ClientDetailDTO clientDetailDTO = clientService.getDetail(command.getClientId());
		ErrorCode.CLIENT_NOT_FOUND.notNull(clientDetailDTO);

		// 校验账号密码
		AccountAuthRequest accountAuthRequest = authConvert.toAccountAuthRequest(command);
		Result<String> validateAccountResult = accountRpcService.validateAccount(accountAuthRequest);
		ErrorCode.LOGIN_BAD_CREDENTIALS.isFalse(validateAccountResult.isSuccess());

		// 获取用户信息
		Result<GetUserInfoResponse> userInfoResult = userRpcService.getUserInfo(validateAccountResult.getData());
		ErrorCode.LOGIN_BAD_CREDENTIALS.isFalse(userInfoResult.isSuccess());

		// 创建用户 Token
		CreateUserTokenCommand createUserTokenCommand = authConvert.toCreateUserTokenCommand(clientDetailDTO, userInfoResult.getData());
		CreateUserTokenDTO userToken = tokenService.createUserToken(createUserTokenCommand);
		return authConvert.toUserLoginDTO(userToken);
	}
}
