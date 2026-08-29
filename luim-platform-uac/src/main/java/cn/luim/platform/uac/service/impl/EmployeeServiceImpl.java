package cn.luim.platform.uac.service.impl;

import cn.luim.platform.uac.common.enums.ErrorCode;
import cn.luim.platform.uac.mapper.entity.EmployeeDO;
import cn.luim.platform.uac.model.convert.EmployeeConvert;
import cn.luim.platform.uac.repository.EmployeeRepository;
import cn.luim.platform.uac.service.DeptService;
import cn.luim.platform.uac.service.EmployeeService;
import cn.luim.platform.uac.service.command.CreateEmployeeCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 员工共相关业务
 *
 * @author yang.lu
 */
@Service
@Validated
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private final EmployeeConvert employeeConvert;
	private final EmployeeRepository employeeRepository;

	private final DeptService deptService;

	@Override
	public void create(@Valid CreateEmployeeCommand command) {

		// 校验部门是否存在
		boolean deptIdValid = deptService.checkDeptExist(command.getDeptId());
		ErrorCode.DEPT_NOT_FOUND.isFalse(deptIdValid);

		// 校验工号是否存在
		boolean employeeNoExist = employeeRepository.isEmployeeNoExist(command.getEmployeeNo());
		ErrorCode.EMPLOYEE_NO_EXISTS.isTrue(employeeNoExist);

		// 校验工号是否存在
		boolean workEmailExist = employeeRepository.isWorkEmailExist(command.getWorkEmail());
		ErrorCode.EMPLOYEE_WORK_EMAIL_EXISTS.isTrue(workEmailExist);

		EmployeeDO employeeDO = employeeConvert.toEmployeeDO(command);
		boolean saveSuccess = employeeRepository.save(employeeDO);
		if (saveSuccess) {
			logger.info("员工创建成功, employeeNo: {}", command.getEmployeeNo());
		}
	}
}
