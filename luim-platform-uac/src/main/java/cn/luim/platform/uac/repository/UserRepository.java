package cn.luim.platform.uac.repository;

import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.platform.uac.mapper.UserMapper;
import cn.luim.platform.uac.mapper.entity.UserDO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * 用户数据仓储
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class UserRepository extends ServiceImpl<UserMapper, UserDO> {

	private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

	public boolean isEmailExist(String email) {
		return this.lambdaQuery()
			.eq(UserDO::getEmail, email)
			.exists();
	}

	public UserDO findByUserId(String userId) {
		if (StringUtil.isBlank(userId)) {
			logger.warn("[用户仓储-查询用户信息] 缺失查询条件 | userId={}", userId);
			return null;
		}

		return this.lambdaQuery()
			.eq(UserDO::getUserId, userId)
			.one();
	}
}
