package cn.luim.platform.uac.service;

import cn.luim.platform.uac.model.dto.UserDetailDTO;

public interface UserService {

	UserDetailDTO getDetail(String userId);
}
