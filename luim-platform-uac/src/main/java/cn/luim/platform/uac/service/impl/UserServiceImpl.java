package cn.luim.platform.uac.service.impl;

import cn.luim.boot.starter.base.utils.ObjectUtil;
import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.platform.uac.model.convert.UserConvert;
import cn.luim.platform.uac.model.dto.UserDetailDTO;
import cn.luim.platform.uac.model.entity.UserDO;
import cn.luim.platform.uac.repository.UserRepository;
import cn.luim.platform.uac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 用户相关业务
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;
	private final UserConvert userConvert;

	@Override
	public UserDetailDTO getDetail(String userId) {

		if (StringUtil.isBlank(userId)) {
			logger.warn("[获取用户详情] 缺失查询参数 | userId={}", userId);
			return null;
		}

		UserDO userDO = userRepository.findByUserId(userId);
		if (ObjectUtil.isNull(userDO)) {
			logger.info("[获取用户详情] 未查询到用户信息 | userId={}", userId);
			return null;
		}

		return userConvert.buildUserDetailDTO(userDO);
	}
}
