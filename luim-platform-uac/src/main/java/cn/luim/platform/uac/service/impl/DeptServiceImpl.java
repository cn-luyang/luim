package cn.luim.platform.uac.service.impl;

import cn.luim.boot.starter.base.enums.StatusEnum;
import cn.luim.boot.starter.base.utils.ObjectUtil;
import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.boot.starter.base.utils.constant.StringPool;
import cn.luim.platform.uac.common.constant.DeptConstant;
import cn.luim.platform.uac.common.enums.ErrorCode;
import cn.luim.platform.uac.controller.request.CreateDeptRequest;
import cn.luim.platform.uac.controller.request.UpdateDeptRequest;
import cn.luim.platform.uac.controller.response.CreateDeptResponse;
import cn.luim.platform.uac.mapper.entity.DeptDO;
import cn.luim.platform.uac.model.convert.DeptConvert;
import cn.luim.platform.uac.repository.DeptRepository;
import cn.luim.platform.uac.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 部门相关业务接口实现
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {

	private static final Logger logger = LoggerFactory.getLogger(DeptServiceImpl.class);

	private final DeptRepository deptRepository;
	private final DeptConvert deptConvert;

	@Override
	public CreateDeptResponse create(CreateDeptRequest createDeptRequest) {

		Long parentId = createDeptRequest.parentId();
		int level = 0;
		String path = StringPool.SLASH;
		Integer sortOrder = createDeptRequest.sortOrder();

		if (ObjectUtil.equals(parentId, DeptConstant.ROOT_DEPT_ID)) {
			// 是否已存在虚拟根（保证全系统唯一）
			boolean hasRootDept = deptRepository.rootDeptExists();
			ErrorCode.DEPT_ROOT_EXISTS.isTrue(hasRootDept);
		} else {
			// 校验父部门是否存在且处于启用状态
			DeptDO parentDept = deptRepository.getById(parentId);
			ErrorCode.DEPT_PARENT_NOT_FOUND.isNull(parentDept);
			ErrorCode.DEPT_PARENT_DISABLED.isTrue(StatusEnum.DISABLED == parentDept.getStatus());

			// 计算当前部门层级，并校验是否超出最大深度限制
			level = parentDept.getLevel() + 1;
			ErrorCode.DEPT_EXCEEDS_DEPTH.isTrue(level >= DeptConstant.MAX_DEPT_DEPTH);

			// 拼接当前部门的路径（格式：/1/2/）
			path = parentDept.getPath() + parentDept.getId() + StringPool.SLASH;

			// 同级部门同名校验
			boolean nameDuplicate = deptRepository.isNameDuplicate(parentId, createDeptRequest.deptName());
			ErrorCode.DEPT_NAME_DUPLICATE.isTrue(nameDuplicate);

			// 处理排序号(sortOrder)更新逻辑
			if (ObjectUtil.isNull(sortOrder)) {
				// 未指定排序号时，取同级部门下当前最大排序号 + 10（若无同级部门则默认从 10 开始）
				Integer maxSort = deptRepository.findMaxSortByParentId(parentId);
				sortOrder = ObjectUtil.defaultIfNull(maxSort, 0) + 10;
			} else if (deptRepository.isSortOrderOccupied(parentId, sortOrder)) {
				// 指定的排序号已被占用：将同级部门中 >= sortOrder 的排序号统一顺延 (+1)
				deptRepository.incrementSortOrderGte(parentId, sortOrder);
			}
		}

		DeptDO deptDO = deptConvert.toDeptDO(createDeptRequest, level, path, sortOrder);
		deptRepository.save(deptDO);

		return CreateDeptResponse.of(deptDO.getId());
	}

	@Override
	public void update(UpdateDeptRequest updateDeptRequest) {

		// 校验当前待修改部门是否存在
		DeptDO currentDeptDO = deptRepository.getById(updateDeptRequest.id());
		ErrorCode.DEPT_NOT_FOUND.isNull(currentDeptDO);

		// 根部门禁止修改
		Long currentParentId = currentDeptDO.getParentId();
		ErrorCode.DEPT_ROOT_NOT_MODIFY.isTrue(ObjectUtil.equals(currentParentId, DeptConstant.ROOT_DEPT_ID));

		// 目标父部门禁止修改为根部门
		Long targetParentId = updateDeptRequest.parentId();
		ErrorCode.DEPT_ROOT_EXISTS.isTrue(ObjectUtil.equals(targetParentId, DeptConstant.ROOT_DEPT_ID));

		Long currentDeptId = currentDeptDO.getId();
		String currentDeptName = currentDeptDO.getDeptName();
		Integer currentLevel = currentDeptDO.getLevel();
		String currentPath = currentDeptDO.getPath();

		// 判断是否变更了父部门
		boolean isParentIdChanged = !ObjectUtil.equals(currentParentId, targetParentId);
		if (isParentIdChanged) {
			// 不能将父部门设置为自己
			ErrorCode.DEPT_PARENT_IS_SELF.isTrue(ObjectUtil.equals(currentDeptId, targetParentId));
			// 目标父部门为根部门
//			if (NumberUtil.equals(targetParentId, DeptConstant.ROOT_DEPT_ID)) {
//				currentLevel = 0;
//				currentPath = StringPool.SLASH;
//			} else {
			DeptDO targetDept = deptRepository.getById(targetParentId);
			// 目标父部门是否存在
			ErrorCode.DEPT_PARENT_NOT_FOUND.isNull(targetDept);
			// 目标父部门是否已禁用
			ErrorCode.DEPT_PARENT_DISABLED.isTrue(StatusEnum.DISABLED == targetDept.getStatus());
			// 目标父部门不能是当前部门的子孙部门（避免环形依赖）
			String currentDeptPathPrefix = currentDeptDO.getPath() + currentDeptId + StringPool.SLASH;
			ErrorCode.DEPT_PARENT_IS_CHILD.isTrue(targetDept.getPath().startsWith(currentDeptPathPrefix));

			// 计算当前部门的新层级与新路径
			currentLevel = targetDept.getLevel() + 1;
			currentPath = targetDept.getPath() + targetDept.getId() + StringPool.SLASH;

			// 校验当前部门层级是否超出最大深度限制
			ErrorCode.DEPT_EXCEEDS_DEPTH.isTrue(currentLevel >= DeptConstant.MAX_DEPT_DEPTH);
//			}

			// 级联更新所有子孙部门的层级(level)和路径(path)
			String currentPathPrefix = currentDeptDO.getPath() + currentDeptDO.getId() + StringPool.SLASH;
			String targetPathPrefix = currentPath + currentDeptDO.getId() + StringPool.SLASH;
			int levelOffset = currentLevel - currentDeptDO.getLevel();
			// 查找并替换所有子孙部门的 path 与 level
			deptRepository.updateChildrenPathAndLevel(currentPathPrefix, targetPathPrefix, levelOffset);
		}

		// 校验部门名称是否重复（变更了父部门 或 修改了部门名称 时触发）
		if (isParentIdChanged || !StringUtil.equals(currentDeptName, updateDeptRequest.deptName())) {
			boolean nameDuplicate = deptRepository.isNameDuplicate(updateDeptRequest.deptName(), targetParentId, currentDeptId);
			ErrorCode.DEPT_NAME_DUPLICATE.isTrue(nameDuplicate);
		}

		// 处理排序号(sortOrder)更新
		Integer sortOrder = updateDeptRequest.sortOrder();
		if (ObjectUtil.isNull(sortOrder)) {
			if (isParentIdChanged) {
				// 变更了父部门且未指定排序号，默认追加到目标父部门下的末尾
				Integer maxSort = deptRepository.findMaxSortByParentId(targetParentId);
				sortOrder = ObjectUtil.defaultIfNull(maxSort, 0) + 10;
			} else {
				// 未变更父部门且未指定排序号，保持原有排序号不变
				sortOrder = currentDeptDO.getSortOrder();
			}
		} else {
			// 指定了排序号时，若变更了父部门或在同父部门内调整了排序号，则触发后续部门排序号顺延
			if (isParentIdChanged || !sortOrder.equals(currentDeptDO.getSortOrder())) {
				deptRepository.incrementSortOrderGte(targetParentId, sortOrder);
			}
		}

		DeptDO deptDO = deptConvert.toDeptDO(updateDeptRequest, currentLevel, currentPath, sortOrder);
		deptRepository.updateById(deptDO);
	}
}
