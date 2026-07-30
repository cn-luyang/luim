package cn.luim.platform.uac.service;

import cn.luim.platform.api.uac.enums.AccountType;
import cn.luim.platform.uac.model.command.AccountCreateCommand;
import cn.luim.platform.uac.model.dto.AccountDetailDTO;

public interface AccountService {

	/**
	 * 创建账号
	 *
	 * @param command 创建参数
	 */
	void create(AccountCreateCommand command);

	/**
	 * 获取账号详情
	 *
	 * @param account     账号
	 * @param accountType 账号类型
	 * @return 账号详情
	 */
	AccountDetailDTO getDetail(String account, AccountType accountType);
}
