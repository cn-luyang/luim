package cn.luim.platform.uac.service.impl;

import cn.luim.boot.starter.base.utils.ObjectUtil;
import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.platform.api.uac.enums.AccountType;
import cn.luim.platform.uac.mapper.entity.AccountDO;
import cn.luim.platform.uac.model.command.AccountCreateCommand;
import cn.luim.platform.uac.model.convert.AccountConvert;
import cn.luim.platform.uac.model.dto.AccountDetailDTO;
import cn.luim.platform.uac.repository.AccountRepository;
import cn.luim.platform.uac.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 账号相关业务
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	private final AccountRepository accountRepository;
	private final AccountConvert accountConvert;


	@Override
	public void create(AccountCreateCommand command) {

	}

	@Override
	public AccountDetailDTO getDetail(String account, AccountType accountType) {

		if (StringUtil.isBlank(account) || ObjectUtil.isNull(accountType)) {
			logger.warn("[获取账号详情] 缺失查询参数 | account={}, accountType={}", account, accountType);
			return null;
		}

		AccountDO accountDO = accountRepository.findByAccountAndType(account, accountType);
		if (ObjectUtil.isNull(accountDO)) {
			logger.info("[获取账号详情] 未查询到账户信息 | account={}, accountType={}", account, accountType);
			return null;
		}

		return accountConvert.toAccountDetailDTO(accountDO);
	}
}
