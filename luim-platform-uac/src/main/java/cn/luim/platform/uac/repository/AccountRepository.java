package cn.luim.platform.uac.repository;

import cn.luim.platform.uac.mapper.AccountMapper;
import cn.luim.platform.uac.model.entity.AccountDO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 账户数据仓储
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class AccountRepository extends ServiceImpl<AccountMapper, AccountDO> {

	private final AccountMapper accountMapper;

}
