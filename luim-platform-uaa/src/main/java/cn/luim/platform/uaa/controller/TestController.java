package cn.luim.platform.uaa.controller;

import cn.luim.boot.starter.base.model.Result;
import cn.luim.boot.starter.security.annotation.Anonymous;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang.lu
 */
@RestController
public class TestController {

	@GetMapping("/testOne")
	public Result<String> testOne(){
		return  Result.success("testOne");
	}

	@Anonymous
	@GetMapping("/testTwo")
	public Result<String> testTwo(){
		return  Result.success("testTwo");
	}

	@GetMapping("/testThree")
	public Result<String> testThree(){
		return  Result.success("testThree");
	}
}
