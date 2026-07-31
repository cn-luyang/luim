package cn.luim.platform.uaa.model.command;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class UserLoginCommand {

	private String account;
	private Integer accountType;
	private String credential;
	private String clientId;
}
