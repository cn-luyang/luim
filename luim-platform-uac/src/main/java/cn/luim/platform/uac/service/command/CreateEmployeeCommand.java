package cn.luim.platform.uac.service.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeCommand {

	@NotNull
	private Long userId;

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
