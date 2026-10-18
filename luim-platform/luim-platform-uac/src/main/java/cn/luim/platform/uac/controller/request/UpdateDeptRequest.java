package cn.luim.platform.uac.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 创建部门请求参数
 *
 * @param deptId    部门ID
 * @param parentId  父部门ID
 * @param deptName  部门名称
 * @param sortOrder 排序序号
 * @param status    状态: 1-启用, 2-禁用
 * @author yang.lu
 */
public record UpdateDeptRequest(

	@NotNull
	Long deptId,

	@NotNull
	Long parentId,

	@NotBlank
	String deptName,

	Integer sortOrder,

	Integer status
) {
}
