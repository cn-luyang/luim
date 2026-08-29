package cn.luim.platform.uac.common.convert;

import cn.luim.boot.starter.base.enums.IBaseEnum;
import cn.luim.platform.uac.common.enums.database.EmployeeType;
import cn.luim.platform.uac.mapper.entity.EmployeeDO;
import cn.luim.platform.uac.service.command.CreateEmployeeCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
	componentModel = "spring",
	imports = {
		IBaseEnum.class,
		EmployeeType.class
	})
public interface EmployeeConvert {

	@Mapping(target = "employeeType", expression = "java(IBaseEnum.getByCode(EmployeeType.class, command.getEmployeeType()))")
	EmployeeDO toEmployeeDO(CreateEmployeeCommand command);
}
