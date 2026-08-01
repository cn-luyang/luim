package cn.luim.platform.uac.service.impl;

import cn.luim.boot.starter.base.utils.ObjectUtil;
import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.platform.uac.mapper.entity.PasswordDO;
import cn.luim.platform.uac.repository.PasswordRepository;
import cn.luim.platform.uac.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 密码相关业务
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

	private static final Logger logger = LoggerFactory.getLogger(PasswordServiceImpl.class);

	private final PasswordRepository passwordRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public boolean validatePassword(String accountId, String credential) {
		if (StringUtil.hasBlank(accountId, credential)) {
			logger.warn("[密码验证] 缺失参数 | accountId={}", accountId);
			return false;
		}

		PasswordDO passwordDO = passwordRepository.findByAccountId(accountId);
		if (ObjectUtil.isNull(passwordDO)) {
			logger.warn("[密码验证] 密码记录不存在 | accountId={}", accountId);
			return false;
		}

		String passwordHash = passwordDO.getPasswordHash();
		return passwordEncoder.matches(credential, passwordHash);
	}
}
