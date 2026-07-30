package cn.luim.platform.uaa.beans.convert;

import cn.luim.boot.starter.base.utils.json.JsonUtil;
import cn.luim.boot.starter.security.context.UserContext;
import cn.luim.platform.uaa.beans.command.CreateUserTokenCommand;
import cn.luim.platform.uaa.beans.dto.ClientDetailDTO;
import cn.luim.platform.uaa.beans.dto.CreateUserTokenDTO;
import cn.luim.platform.uaa.beans.entity.TokenDO;
import cn.luim.platform.uaa.common.utils.TokenUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

/**
 * Token 相关对象转换
 *
 * @author yang.lu
 */
@Mapper(componentModel = "spring")
public interface TokenConvert {

	default TokenDO buildEntity(ClientDetailDTO clientDetailDTO, UserContext userContext) {

		TokenDO tokenDO = new TokenDO();
		tokenDO.setClientId(clientDetailDTO.clientId());
		tokenDO.setUserId(userContext.getUserId());
		tokenDO.setAccessToken(TokenUtil.generateAccessToken());
		tokenDO.setRefreshToken(TokenUtil.generateRefreshToken());

		LocalDateTime now = LocalDateTime.now();

		tokenDO.setAccessTokenIssuedTime(now);
		tokenDO.setAccessTokenExpiresTime(now.plusSeconds(clientDetailDTO.accessTokenValidity()));
		tokenDO.setRefreshTokenExpiresTime(now.plusSeconds(clientDetailDTO.refreshTokenValidity()));
		tokenDO.setExtraInfo(JsonUtil.toMap(userContext, String.class, Object.class));

		return tokenDO;
	}

	default TokenDO buildEntity(CreateUserTokenCommand command) {

		TokenDO tokenDO = new TokenDO();
		tokenDO.setClientId(command.getClientId());
		tokenDO.setUserId(command.getUserId());
		tokenDO.setAccessToken(TokenUtil.generateAccessToken());
		tokenDO.setRefreshToken(TokenUtil.generateRefreshToken());

		LocalDateTime now = LocalDateTime.now();

		tokenDO.setAccessTokenIssuedTime(now);
		tokenDO.setAccessTokenExpiresTime(now.plusSeconds(command.getAccessTokenValidity()));
		tokenDO.setRefreshTokenExpiresTime(now.plusSeconds(command.getRefreshTokenValidity()));
		tokenDO.setExtraInfo(command.getExtraInfo());

		return tokenDO;
	}

	@Mapping(source = "accessTokenExpiresTime", target = "expiresTime")
	CreateUserTokenDTO buildCreateUserTokenDTO(TokenDO tokenDO);
}
