package cn.luim.platform.uac.service;

import cn.luim.platform.uac.controller.request.CreateDeptRequest;
import cn.luim.platform.uac.controller.request.UpdateDeptRequest;
import cn.luim.platform.uac.controller.response.CreateDeptResponse;

/**
 * 部门相关业务接口
 *
 * @author yang.lu
 */
public interface DeptService {

	CreateDeptResponse create(CreateDeptRequest createDeptRequest);

	void update(UpdateDeptRequest updateDeptRequest);
}
