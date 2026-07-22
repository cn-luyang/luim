package cn.luim.platform.uac.model.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class AccountCreateCommand {

	@NotBlank
	private String userId;
	private String mobile;
	private String email;
}
