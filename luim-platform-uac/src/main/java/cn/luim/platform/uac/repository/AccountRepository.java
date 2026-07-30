package cn.luim.platform.uac.repository;

import cn.luim.boot.starter.base.utils.ObjectUtil;
import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.platform.api.uac.enums.AccountType;
import cn.luim.platform.uac.mapper.AccountMapper;
import cn.luim.platform.uac.model.entity.AccountDO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * 账户数据仓储
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class AccountRepository extends ServiceImpl<AccountMapper, AccountDO> {

	private static final Logger logger = LoggerFactory.getLogger(AccountRepository.class);

	private final AccountMapper accountMapper;

	/**
	 * 根据账号与账号类型查询账号实体
	 *
	 * @param account     账号
	 * @param accountType 账号类型
	 * @return 账号实体，若参数不全或未查到则返回 null
	 */
	public AccountDO findByAccountAndType(String account, AccountType accountType) {
		if (StringUtil.isBlank(account) || ObjectUtil.isNull(accountType)) {
			logger.warn("[仓储查询-账号] 缺失查询条件 | account={}, accountType={}", account, accountType);
			return null;
		}

		return this.lambdaQuery()
			.eq(AccountDO::getAccount, account)
			.eq(AccountDO::getAccountType, accountType)
			.one();
	}
}
