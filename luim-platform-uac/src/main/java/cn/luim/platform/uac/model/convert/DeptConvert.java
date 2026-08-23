package cn.luim.platform.uac.model.convert;

import cn.luim.platform.uac.controller.request.CreateDeptRequest;
import cn.luim.platform.uac.controller.request.UpdateDeptRequest;
import cn.luim.platform.uac.mapper.entity.DeptDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeptConvert {

	@Mapping(target = "sortOrder", source = "sortOrder")
	DeptDO toDeptDO(CreateDeptRequest createDeptRequest, int deptLevel, String deptPath, Integer sortOrder);

	DeptDO toDeptDO(UpdateDeptRequest updateDeptRequest, int level, String path, Integer sortOrder);
}
