package cn.luim.platform.uac.repository;

import cn.luim.boot.starter.base.utils.ObjectUtil;
import cn.luim.boot.starter.redisson.helper.RedissonHelper;
import cn.luim.platform.uac.common.constant.DeptConstant;
import cn.luim.platform.uac.mapper.DeptMapper;
import cn.luim.platform.uac.mapper.entity.DeptDO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.time.Duration;

/**
 * 部门数据仓储
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class DeptRepository extends ServiceImpl<DeptMapper, DeptDO> {

	private final RedissonHelper redissonHelper;

	@Override
	public boolean save(DeptDO entity) {
		return super.save(entity);
	}

	@Override
	public DeptDO getById(Serializable id) {
		if (ObjectUtil.isNull(id)) {
			return null;
		}

		// 查询缓存
		String cacheKey = DeptConstant.CACHE_DEPT_INFO + id;
		DeptDO cachedDept = redissonHelper.getString(cacheKey);
		if (null != cachedDept) {
			return cachedDept;
		}

		// 查询数据库
		DeptDO dbDept = super.getById(id);
		if (null == dbDept) {
			return null;
		}

		// 添加缓存30分钟有效期
		redissonHelper.setString(cacheKey, dbDept, Duration.ofSeconds(1800));
		return dbDept;
	}

	/**
	 * 校验同级下部门名称是否重复
	 *
	 * @param parentId 父部门 ID
	 * @param deptName 部门名称
	 */
	public boolean isDeptNameExist(Long parentId, String deptName) {
		return this.lambdaQuery()
			.eq(DeptDO::getParentId, parentId)
			.eq(DeptDO::getDeptName, deptName)
			.exists();
	}

	/**
	 * 校验同级下部门名称是否重复（修改部门时使用，排除当前部门自身）
	 *
	 * @param deptName      待校验的部门名称
	 * @param parentId      目标父部门 ID
	 * @param excludeDeptId 需要排除的部门 ID（通常为当前修改的部门 ID）
	 * @return 是否存在重复名称的部门
	 */
	public boolean isDeptNameExist(String deptName, Long parentId, Long excludeDeptId) {
		return this.lambdaQuery()
			.eq(DeptDO::getParentId, parentId)
			.eq(DeptDO::getDeptName, deptName)
			.ne(DeptDO::getDeptId, excludeDeptId)
			.exists();
	}

	/**
	 * 获取同级部门的最大排序号
	 *
	 * @param parentId 父部门 ID
	 * @return 最大排序号（若无子部门则返回 null）
	 */
	public Integer findMaxSortByParentId(Long parentId) {
		return baseMapper.selectMaxSortByParentId(parentId);
	}

	/**
	 * 将同级大于等于目标序号的部门统一后移 (+1)
	 *
	 * @param parentId  父部门 ID
	 * @param sortOrder 起始排序号
	 */
	public void incrementSortOrderGte(Long parentId, Integer sortOrder) {
		this.lambdaUpdate()
			.eq(DeptDO::getParentId, parentId)
			.ge(DeptDO::getSortOrder, sortOrder)
			.setSql("sort_order = sort_order + 1")
			.update();
	}

	/**
	 * 检查同级下指定的排序号是否已被占用
	 *
	 * @param parentId  父部门 ID
	 * @param sortOrder 排序号
	 */
	public boolean isSortOrderOccupied(Long parentId, Integer sortOrder) {
		return lambdaQuery()
			.eq(DeptDO::getParentId, parentId)
			.eq(DeptDO::getSortOrder, sortOrder)
			.exists();
	}

	/**
	 * 级联批量更新所有子孙部门的路径(path)与层级(level)
	 *
	 * @param currentPathPrefix 变更前的子孙部门路径前缀（如：/1/2/）
	 * @param targetPathPrefix  变更后的子孙部门路径前缀（如：/1/3/2/）
	 * @param levelOffset       层级偏移量（新层级 - 旧层级）
	 */
	public void updateChildrenPathAndLevel(String currentPathPrefix, String targetPathPrefix, int levelOffset) {
		this.baseMapper.updateChildrenPathAndLevel(currentPathPrefix, targetPathPrefix, levelOffset);
	}

	public boolean rootDeptExists() {
		return this.lambdaQuery()
			.eq(DeptDO::getParentId, DeptConstant.ROOT_DEPT_ID)
			.exists();
	}

	/**
	 * 部门 ID 是否存在
	 *
	 * @param deptId 部门ID
	 * @return true存在 false不存在
	 */
	public boolean isDeptIdExist(Long deptId) {
		Assert.notNull(deptId, "缺失查询条件");
		return getById(deptId) != null;
	}
}
