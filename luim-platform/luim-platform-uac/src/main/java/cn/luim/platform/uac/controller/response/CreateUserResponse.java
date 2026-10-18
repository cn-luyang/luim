package cn.luim.platform.uac.controller.response;

/**
 * 创建用户响应参数
 *
 * @author yang.lu
 */
public record CreateUserResponse(Long userId) {

	public static CreateUserResponse of(Long userId) {
		return new CreateUserResponse(userId);
	}
}
