package cn.luim.platform.uac.service.impl;

import cn.luim.platform.uac.model.command.AccountCreateCommand;
import cn.luim.platform.uac.model.convert.AccountConvert;
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
}
