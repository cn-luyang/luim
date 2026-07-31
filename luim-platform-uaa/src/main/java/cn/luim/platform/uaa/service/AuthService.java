package cn.luim.platform.uaa.service;

import cn.luim.platform.uaa.model.command.UserLoginCommand;
import cn.luim.platform.uaa.model.dto.UserLoginDTO;

public interface AuthService {

	UserLoginDTO login(UserLoginCommand command);
}
