package cn.luim.platform.uac.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 创建部门请求参数
 *
 * @param id        部门ID
 * @param parentId  父部门ID
 * @param deptName  部门名称
 * @param sortOrder 排序序号
 * @param status    状态: 1-启用, 2-禁用
 * @author yang.lu
 */
public record UpdateDeptRequest(

	@NotNull(message = "部门ID为空")
	Long id,

	@NotNull(message = "父部门ID为空")
	Long parentId,

	@NotBlank(message = "部门名称为空")
	String deptName,

	Integer sortOrder,

	Integer status
) {
}
