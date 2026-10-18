package cn.luim.platform.uac.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

	@NotBlank
	@Email
	private String email;

	@NotNull
	private Integer userType;

	@Valid
	private EmployeeInfo employeeInfo;

	@Getter
	@Setter
	public static class EmployeeInfo {

		@NotNull
		private Long deptId;

		@NotBlank
		private String employeeNo;

		@NotBlank
		@Email
		private String workEmail;

		@NotNull
		private Integer employeeType;
	}
}
