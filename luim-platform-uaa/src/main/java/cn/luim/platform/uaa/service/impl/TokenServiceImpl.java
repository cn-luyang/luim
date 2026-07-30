package cn.luim.platform.uaa.service.impl;

import cn.luim.boot.starter.base.utils.ObjectUtil;
import cn.luim.platform.uaa.beans.command.CreateUserTokenCommand;
import cn.luim.platform.uaa.beans.convert.TokenConvert;
import cn.luim.platform.uaa.beans.dto.CreateUserTokenDTO;
import cn.luim.platform.uaa.beans.entity.TokenDO;
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
	public CreateUserTokenDTO createUserToken(CreateUserTokenCommand command) {

		// 查询当前客户端下是否存在 Token
		Long tokenId = tokenRepository.findValidTokenId(command.getClientId(), command.getUserId());
		if (ObjectUtil.notNull(tokenId)) {
			// 注销当前客户端下 Token
			tokenRepository.removeById(tokenId);
		}

		TokenDO tokenDO = tokenConvert.buildEntity(command);
		tokenRepository.save(tokenDO);

		return tokenConvert.buildCreateUserTokenDTO(tokenDO);
	}
}
