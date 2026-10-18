package cn.luim.platform.uac.repository;

import cn.luim.platform.uac.mapper.EmployeeMapper;
import cn.luim.platform.uac.mapper.entity.EmployeeDO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 员工数据仓储
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class EmployeeRepository extends ServiceImpl<EmployeeMapper, EmployeeDO> {

	public boolean isEmployeeNoExist(String employeeNo) {
		Assert.hasText(employeeNo, "缺失查询条件");
		return this.lambdaQuery()
			.eq(EmployeeDO::getEmployeeNo, employeeNo)
			.exists();
	}

	public boolean isWorkEmailExist(String workEmail) {
		Assert.hasText(workEmail, "缺失查询条件");
		return this.lambdaQuery()
			.eq(EmployeeDO::getWorkEmail, workEmail)
			.exists();
	}
}
