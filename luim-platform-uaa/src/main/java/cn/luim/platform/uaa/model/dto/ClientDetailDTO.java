package cn.luim.platform.uaa.model.dto;

import java.util.List;

/**
 * @author yang.lu
 */
public record ClientDetailDTO(
	String clientId,
	String clientSecret,
	List<String> redirectUris,
	Integer accessTokenValidity,
	Integer refreshTokenValidity,
	String description
) {

}
