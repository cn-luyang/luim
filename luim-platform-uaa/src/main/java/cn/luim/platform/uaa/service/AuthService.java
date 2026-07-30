package cn.luim.platform.uaa.service;

import cn.luim.platform.uaa.beans.command.LoginCommand;

public interface AuthService {

	void login(LoginCommand command);
}
