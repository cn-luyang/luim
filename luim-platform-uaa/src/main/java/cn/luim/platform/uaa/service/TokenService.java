package cn.luim.platform.uaa.service;

import cn.luim.platform.uaa.model.command.CreateUserTokenCommand;
import cn.luim.platform.uaa.model.dto.CreateUserTokenDTO;

/**
 * @author yang.lu
 */
public interface TokenService {

	CreateUserTokenDTO createUserToken(CreateUserTokenCommand command);
}
