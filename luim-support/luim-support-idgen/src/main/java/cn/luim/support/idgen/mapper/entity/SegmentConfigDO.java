package cn.luim.support.idgen.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "id_segment_config", autoResultMap = true)
public class SegmentConfigDO {

	@TableId("id")
	private Long deptId;
	private String businessCode;
	private Integer idPrefix;
	private Integer initStep;
	private Integer minStep;
	private Integer maxStep;
	private String description;
	private Integer status;
}
