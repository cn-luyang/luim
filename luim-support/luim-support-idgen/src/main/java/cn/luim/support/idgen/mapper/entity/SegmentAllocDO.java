package cn.luim.support.idgen.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "id_segment_config", autoResultMap = true)
public class SegmentAllocDO {

	@TableId("id")
	private Long deptId;
	private String businessCode;
	private Integer currentMax;
	private Integer currentStep;
	private LocalDateTime updateTime;
}
