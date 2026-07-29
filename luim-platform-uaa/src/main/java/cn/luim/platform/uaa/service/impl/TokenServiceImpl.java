package cn.luim.platform.uaa.service.impl;

import cn.luim.boot.starter.base.utils.ObjectUtil;
import cn.luim.boot.starter.security.context.UserContext;
import cn.luim.platform.uaa.common.enums.ErrorCode;
import cn.luim.platform.uaa.model.convert.TokenConvert;
import cn.luim.platform.uaa.model.dto.ClientDetailDTO;
import cn.luim.platform.uaa.model.dto.CreateUserTokenDTO;
import cn.luim.platform.uaa.model.entity.TokenDO;
import cn.luim.platform.uaa.repository.TokenRepository;
import cn.luim.platform.uaa.service.ClientService;
import cn.luim.platform.uaa.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

	private final TokenConvert tokenConvert;
	private final TokenRepository tokenRepository;

	private final ClientService clientService;

	@Override
	public CreateUserTokenDTO createUserToken(String clientId, String userId) {

		// 获取客户端信息
		ClientDetailDTO clientDetailDTO = clientService.getDetail(clientId);
		ErrorCode.CLIENT_NOT_FOUND.notNull(clientDetailDTO);

		// 查询当前客户端下是否存在 Token
		Long tokenId = tokenRepository.findValidTokenId(clientId, userId);
		if (ObjectUtil.notNull(tokenId)) {
			// 注销当前客户端下 Token
		}

		// 查询用户信息
		UserContext userContext = null;

		TokenDO tokenDO = tokenConvert.buildEntity(clientDetailDTO, userContext);
		tokenRepository.save(tokenDO);

		return tokenConvert.buildCreateUserTokenDTO(tokenDO);
	}

	public void revokeToken(String clientId, String userId) {

	}
}
