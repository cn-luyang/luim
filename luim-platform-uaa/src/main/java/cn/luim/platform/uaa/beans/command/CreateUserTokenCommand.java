package cn.luim.platform.uaa.beans.command;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class CreateUserTokenCommand {

	private String clientId;
	private String userId;
	private Integer accessTokenValidity;
	private Integer refreshTokenValidity;
	private Map<String, Object> extraInfo;
}
