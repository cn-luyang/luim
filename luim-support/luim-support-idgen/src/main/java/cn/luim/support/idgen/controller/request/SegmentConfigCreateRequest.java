package cn.luim.support.idgen.controller.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class SegmentConfigCreateRequest {

	public String businessCode;
	public Integer initStep;
	public Integer minStep;
	public Integer maxStep;
	public String description;
}
