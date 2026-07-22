package cn.luim.platform.uac.service;

import cn.luim.platform.uac.model.command.AccountCreateCommand;

public interface AccountService {

	public void create(AccountCreateCommand command);
}
