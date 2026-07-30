package cn.luim.platform.uac.model.convert;

import cn.luim.platform.api.uac.model.response.GetUserInfoResponse;
import cn.luim.platform.uac.model.dto.UserDetailDTO;
import cn.luim.platform.uac.model.entity.UserDO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConvert {

	UserDetailDTO buildUserDetailDTO(UserDO userDO);

	GetUserInfoResponse buildGetUserInfoResponse(UserDetailDTO userDetailDTO);
}
