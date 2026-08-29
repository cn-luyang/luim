package cn.luim.platform.uac.model.convert;

import cn.luim.boot.starter.base.enums.IBaseEnum;
import cn.luim.boot.starter.base.utils.id.IdUtil;
import cn.luim.platform.api.uac.model.response.GetUserInfoResponse;
import cn.luim.platform.uac.common.enums.database.UserType;
import cn.luim.platform.uac.controller.request.CreateUserRequest;
import cn.luim.platform.uac.mapper.entity.UserDO;
import cn.luim.platform.uac.model.dto.UserDetailDTO;
import cn.luim.platform.uac.service.command.CreateEmployeeCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
	componentModel = "spring",
	imports = {
		IBaseEnum.class,
		UserType.class,
		IdUtil.class
	})
public interface UserConvert {

	@Mapping(target = "userId", expression = "java(IdUtil.getSnowflakeNextId())")
	@Mapping(target = "userType", expression = "java(IBaseEnum.getByCode(UserType.class, createUserRequest.getUserType()))")
	UserDO toUserDO(CreateUserRequest createUserRequest);

	UserDetailDTO buildUserDetailDTO(UserDO userDO);

	GetUserInfoResponse toGetUserInfoResponse(UserDetailDTO userDetailDTO);

	CreateEmployeeCommand toCreateEmployeeCommand(Long userId, CreateUserRequest.EmployeeInfo employeeInfo);
}
