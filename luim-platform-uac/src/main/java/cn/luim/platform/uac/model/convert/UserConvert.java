package cn.luim.platform.uac.model.convert;

import cn.luim.platform.api.uac.model.response.GetUserInfoResponse;
import cn.luim.platform.uac.controller.request.CreateUserRequest;
import cn.luim.platform.uac.mapper.entity.UserDO;
import cn.luim.platform.uac.model.dto.UserDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConvert {

	UserDO toUserDO(CreateUserRequest createUserRequest);

	UserDetailDTO buildUserDetailDTO(UserDO userDO);

	GetUserInfoResponse toGetUserInfoResponse(UserDetailDTO userDetailDTO);
}
