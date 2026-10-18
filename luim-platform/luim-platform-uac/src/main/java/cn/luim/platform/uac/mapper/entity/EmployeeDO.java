package cn.luim.platform.uac.mapper.entity;

import cn.luim.platform.uac.common.enums.database.EmployeeType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "uac_employee", autoResultMap = true)
public class EmployeeDO {

	@TableId("id")
	private Long employeeId;
	private Long userId;
	private String employeeNo;
	private String workEmail;
	private EmployeeType employeeType;
}
