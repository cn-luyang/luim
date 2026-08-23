package cn.luim.platform.uac.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 创建部门请求参数
 *
 * @param deptName  部门名称
 * @param parentId  父部门ID
 * @param sortOrder 排序序号
 * @author yang.lu
 */
public record CreateDeptRequest(

	@NotBlank
	String deptName,

	@NotNull
	Long parentId,

	Integer sortOrder
) {

}
