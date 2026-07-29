package cn.luim.platform.uac.model.command;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class AccountCreateCommand {

	private String userId;
	private String mobile;
	private String email;
}
