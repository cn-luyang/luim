package cn.luim.platform.uac.controller.response;

/**
 * 创建部门响应参数
 *
 * @author yang.lu
 */
public record CreateDeptResponse(Long deptId) {

	public static CreateDeptResponse of(Long deptId) {
		return new CreateDeptResponse(deptId);
	}
}
