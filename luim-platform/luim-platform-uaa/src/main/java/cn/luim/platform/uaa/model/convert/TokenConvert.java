package cn.luim.platform.uaa.model.convert;

import cn.luim.platform.uaa.common.utils.TokenUtil;
import cn.luim.platform.uaa.mapper.entity.TokenDO;
import cn.luim.platform.uaa.model.command.CreateUserTokenCommand;
import cn.luim.platform.uaa.model.dto.CreateUserTokenDTO;
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

//	default TokenDO toEntity(ClientDetailDTO clientDetailDTO, UserContext userContext) {
//
//		TokenDO tokenDO = new TokenDO();
//		tokenDO.setClientId(clientDetailDTO.clientId());
//		tokenDO.setUserId(userContext.getUserId());
//		tokenDO.setAccessToken(TokenUtil.generateAccessToken());
//		tokenDO.setRefreshToken(TokenUtil.generateRefreshToken());
//
//		LocalDateTime now = LocalDateTime.now();
//
//		tokenDO.setAccessTokenIssuedTime(now);
//		tokenDO.setAccessTokenExpiresTime(now.plusSeconds(clientDetailDTO.accessTokenValidity()));
//		tokenDO.setRefreshTokenExpiresTime(now.plusSeconds(clientDetailDTO.refreshTokenValidity()));
//		tokenDO.setExtraInfo(JsonUtil.toMap(userContext, String.class, Object.class));
//
//		return tokenDO;
//	}

	default TokenDO toEntity(CreateUserTokenCommand command) {

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
	CreateUserTokenDTO toCreateUserTokenDTO(TokenDO tokenDO);
}
