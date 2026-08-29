package cn.luim.platform.uac.service;

import cn.luim.platform.uac.service.command.CreateEmployeeCommand;

public interface EmployeeService {

	public void create(CreateEmployeeCommand command);
}
