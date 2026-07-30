package cn.luim.platform.uaa.beans.convert;

import cn.luim.boot.starter.base.enums.IBaseEnum;
import cn.luim.boot.starter.base.utils.json.JsonUtil;
import cn.luim.platform.api.uac.enums.AccountType;
import cn.luim.platform.api.uac.model.request.AccountAuthRequest;
import cn.luim.platform.api.uac.model.response.GetUserInfoResponse;
import cn.luim.platform.uaa.beans.command.CreateUserTokenCommand;
import cn.luim.platform.uaa.beans.command.LoginCommand;
import cn.luim.platform.uaa.beans.dto.ClientDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 认证相关对象转换
 *
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {
		IBaseEnum.class,
		AccountType.class,
		JsonUtil.class
	})
public interface AuthConvert {

	@Mapping(target = "accountType", expression = "java(IBaseEnum.getByCode(AccountType.class, command.getAccountType()))")
	AccountAuthRequest buildAccountAuthRequest(LoginCommand command);

	@Mapping(target = "extraInfo", expression = "java(JsonUtil.toMap(getUserInfoResponse, String.class, Object.class))")
	CreateUserTokenCommand buildCreateUserTokenCommand(ClientDetailDTO clientDetailDTO, GetUserInfoResponse getUserInfoResponse);
}
