package cn.luim.platform.uac.mapper.entity;

import cn.luim.boot.starter.mybatis.enums.StatusEnum;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 部门表实体
 *
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "uac_dept", autoResultMap = true)
public class DeptDO {

	@TableId("id")
	private Long deptId;
	private Long parentId;
	private String deptName;
	private Integer deptLevel;
	private String deptPath;
	private Integer sortOrder;
	private StatusEnum status;
}
