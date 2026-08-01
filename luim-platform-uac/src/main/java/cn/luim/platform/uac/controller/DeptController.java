package cn.luim.platform.uac.controller;

import cn.luim.boot.starter.base.model.Result;
import cn.luim.boot.starter.security.annotation.Anonymous;
import cn.luim.platform.uac.controller.request.CreateDeptRequest;
import cn.luim.platform.uac.controller.request.UpdateDeptRequest;
import cn.luim.platform.uac.controller.response.CreateDeptResponse;
import cn.luim.platform.uac.service.DeptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门相关前端接口
 *
 * @author yang.lu
 */
@Anonymous
@RestController
@RequiredArgsConstructor
@RequestMapping("/dept")
public class DeptController {

	private final DeptService deptService;

	/**
	 * 创建部门
	 *
	 * @param createDeptRequest 创建部门请求参数
	 * @return 创建成功的部门响应
	 */
	@PostMapping
	public Result<CreateDeptResponse> create(@Valid @RequestBody CreateDeptRequest createDeptRequest) {
		return Result.success(deptService.create(createDeptRequest));
	}

	/**
	 * 修改部门信息
	 *
	 * @param updateDeptRequest 修改部门请求参数
	 * @return 无
	 */
	@PutMapping
	public Result<Void> update(@Valid @RequestBody UpdateDeptRequest updateDeptRequest) {
		deptService.update(updateDeptRequest);
		return Result.success();
	}
}
