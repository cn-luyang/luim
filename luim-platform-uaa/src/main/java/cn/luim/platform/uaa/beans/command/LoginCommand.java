package cn.luim.platform.uaa.beans.command;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class LoginCommand {

	private String account;
	private Integer accountType;
	private String credential;
	private String clientId;
}
