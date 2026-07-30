package cn.luim.platform.uaa.service;

import cn.luim.platform.uaa.beans.command.CreateUserTokenCommand;
import cn.luim.platform.uaa.beans.dto.CreateUserTokenDTO;

/**
 * @author yang.lu
 */
public interface TokenService {

	CreateUserTokenDTO createUserToken(CreateUserTokenCommand command);
}
