package cn.luim.platform.uac.service.impl;

import cn.luim.boot.starter.base.utils.ObjectUtil;
import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.platform.uac.common.convert.UserConvert;
import cn.luim.platform.uac.common.enums.ErrorCode;
import cn.luim.platform.uac.common.enums.database.UserType;
import cn.luim.platform.uac.controller.request.CreateUserRequest;
import cn.luim.platform.uac.controller.response.CreateUserResponse;
import cn.luim.platform.uac.mapper.entity.UserDO;
import cn.luim.platform.uac.repository.UserRepository;
import cn.luim.platform.uac.service.EmployeeService;
import cn.luim.platform.uac.service.UserService;
import cn.luim.platform.uac.service.command.CreateEmployeeCommand;
import cn.luim.platform.uac.service.dto.UserDetailDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	private final EmployeeService employeeService;

	@Transactional
	@Override
	public CreateUserResponse create(CreateUserRequest createUserRequest) {

		// 校验邮箱是否存在
		boolean emailExist = userRepository.isEmailExist(createUserRequest.getEmail());
		ErrorCode.USER_EMAIL_EXISTS.isTrue(emailExist);

		UserDO userDO = userConvert.toUserDO(createUserRequest);
		userRepository.save(userDO);

		if (UserType.isEmployee(createUserRequest.getUserType())) {
			CreateEmployeeCommand command = userConvert.toCreateEmployeeCommand(
				userDO.getUserId(),
				createUserRequest.getEmployeeInfo()
			);
			employeeService.create(command);
		}

		return CreateUserResponse.of(userDO.getUserId());
	}

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
