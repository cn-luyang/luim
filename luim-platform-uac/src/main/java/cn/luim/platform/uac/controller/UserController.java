package cn.luim.platform.uac.controller;

import cn.luim.boot.starter.base.model.Result;
import cn.luim.boot.starter.security.annotation.Anonymous;
import cn.luim.platform.uac.controller.request.CreateUserRequest;
import cn.luim.platform.uac.controller.response.CreateUserResponse;
import cn.luim.platform.uac.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关控制器
 *
 * @author yang.lu
 */
@Anonymous
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@PostMapping
	public Result<CreateUserResponse> create(@Valid @RequestBody CreateUserRequest createUserRequest) {
		return Result.success(userService.create(createUserRequest));
	}
}
