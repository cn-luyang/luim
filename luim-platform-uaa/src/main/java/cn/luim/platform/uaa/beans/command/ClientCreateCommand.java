package cn.luim.platform.uaa.beans.command;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class ClientCreateCommand {

	private String clientName;
	private List<String> redirectUris;
	private Integer accessTokenValidity;
	private Integer refreshTokenValidity;
	private String description;
}
