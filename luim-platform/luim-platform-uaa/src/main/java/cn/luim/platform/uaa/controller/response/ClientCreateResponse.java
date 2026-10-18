package cn.luim.platform.uaa.controller.response;

/**
 * @author yang.lu
 */
public record ClientCreateResponse(
	String clientId,
	String rawSecret
) {
	public static ClientCreateResponse of(String clientId, String rawSecret) {
		return new ClientCreateResponse(clientId, rawSecret);
	}
}
