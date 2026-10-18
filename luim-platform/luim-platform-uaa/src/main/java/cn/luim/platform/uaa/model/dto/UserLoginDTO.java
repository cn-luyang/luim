package cn.luim.platform.uaa.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class UserLoginDTO {
	String userId;
	String accessToken;
	String refreshToken;
	LocalDateTime expiresTime;
}
