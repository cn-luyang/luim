package cn.luim.platform.uac.mapper.entity;

import cn.luim.boot.starter.base.enums.StatusEnum;
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
@TableName(value = "t_dept", autoResultMap = true)
public class DeptDO {

	@TableId
	private Long id;
	private Long parentId;
	private String deptName;
	private Integer level;
	private String path;
	private Integer sortOrder;
	private StatusEnum status;
}
