package cn.luim.platform.uaa.model.dto;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
public record CreateUserTokenDTO(
	String userId,
	String accessToken,
	String refreshToken,
	LocalDateTime expiresTime
) {
}
