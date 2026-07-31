package cn.luim.platform.uaa.model.dto;

/**
 * @author yang.lu
 */
public record ClientCreateDTO(
	String clientId,
	String rawSecret
) {
	public static ClientCreateDTO of(String clientId, String rawSecret) {
		return new ClientCreateDTO(clientId, rawSecret);
	}
}
