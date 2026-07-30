package cn.luim.platform.uaa.controller;

import cn.luim.boot.starter.base.model.Result;
import cn.luim.boot.starter.security.annotation.Anonymous;
import cn.luim.platform.uaa.beans.command.LoginCommand;
import cn.luim.platform.uaa.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证相关控制器
 *
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	@Anonymous
	@PostMapping("/login")
	public Result<Void> login(@RequestBody LoginCommand command) {
		authService.login(command);
		return null;
	}
}
