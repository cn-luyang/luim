package cn.luim.platform.uac.repository;

import cn.luim.platform.uac.model.entity.AccountDO;
import cn.luim.platform.uac.mapper.AccountMapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class AccountRepository extends ServiceImpl<AccountMapper, AccountDO> {

	private final AccountMapper accountMapper;

	public boolean existsAccount(AccountType accountType, String account) {
		return lambdaQuery()
			.eq(AccountDO::getAccount, account)
			.eq(AccountDO::getAccountType, accountType.getCode())
			.exists();
	}
}
