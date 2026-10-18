package cn.luim.platform.uac.service;

import cn.luim.platform.uac.controller.request.CreateUserRequest;
import cn.luim.platform.uac.controller.response.CreateUserResponse;
import cn.luim.platform.uac.service.dto.UserDetailDTO;
import jakarta.validation.Valid;

public interface UserService {

	CreateUserResponse create(@Valid CreateUserRequest createUserRequest);

	UserDetailDTO getDetail(String userId);
}
