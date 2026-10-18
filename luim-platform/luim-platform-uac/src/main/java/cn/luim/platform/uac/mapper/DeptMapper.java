package cn.luim.platform.uac.mapper;

import cn.luim.platform.uac.mapper.entity.DeptDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 部门 Mapper 接口
 *
 * @author yang.lu
 */
@Mapper
public interface DeptMapper extends BaseMapper<DeptDO> {

	@Select("SELECT MAX(sort_order) FROM t_dept WHERE parent_id = #{parentId}")
	Integer selectMaxSortByParentId(@Param("parentId") Long parentId);

	@Update("UPDATE t_dept " +
		"SET path = REPLACE(path, #{currentPathPrefix}, #{targetPathPrefix}), " +
		"    level = level + #{levelOffset} " +
		"WHERE path LIKE CONCAT(#{currentPathPrefix}, '%')")
	void updateChildrenPathAndLevel(@Param("currentPathPrefix") String currentPathPrefix,
	                                @Param("targetPathPrefix") String targetPathPrefix,
	                                @Param("levelOffset") int levelOffset);
}
