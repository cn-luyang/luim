package cn.luim.platform.uac.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 创建用户请求参数
 *
 * @author yang.lu
 */
@Getter
@Setter
public class CreateUserRequest {

	@NotBlank
	private String realName;
	@Email
	private String email;
}
