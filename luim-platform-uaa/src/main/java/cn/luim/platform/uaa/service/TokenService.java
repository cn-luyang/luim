package cn.luim.platform.uaa.service;

import cn.luim.platform.uaa.model.dto.CreateUserTokenDTO;

/**
 * @author yang.lu
 */
public interface TokenService {

	CreateUserTokenDTO createUserToken(String clientId, String userId);
}
