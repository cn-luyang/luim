package cn.luim.platform.uac.service.dto;

import cn.luim.platform.uac.common.enums.database.UserType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class UserDetailDTO {

	private Long userId;
	private String realName;
	private String email;
	private UserType userType;
}
