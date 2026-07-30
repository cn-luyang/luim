package cn.luim.platform.uaa.beans.dto;

/**
 * @author yang.lu
 */
public record ClientCreateDTO(
	String clientId,
	String clientSecret
) {
	public static ClientCreateDTO build(String clientId, String clientSecret) {
		return new ClientCreateDTO(clientId, clientSecret);
	}
}
