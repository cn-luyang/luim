package cn.luim.platform.uac.repository;

import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.platform.uac.mapper.PasswordMapper;
import cn.luim.platform.uac.mapper.entity.PasswordDO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * 密码数据仓储
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class PasswordRepository extends ServiceImpl<PasswordMapper, PasswordDO> {

	private static final Logger logger = LoggerFactory.getLogger(PasswordRepository.class);

	public PasswordDO findByAccountId(String accountId) {
		if (StringUtil.isBlank(accountId)) {
			logger.warn("[仓储查询-密码] 缺失查询条件 | accountId={}", accountId);
			return null;
		}

		return this.lambdaQuery()
			.eq(PasswordDO::getAccountId, accountId)
			.oneOpt()
			.orElse(null);
	}
}
