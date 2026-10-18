package cn.luim.boot.starter.idgen.dubbo.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 号段申请响应
 *
 * @author yang.lu
 */
@Getter
@Setter
public class GetSegmentResponse implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/** ID 前缀（编入高位） */
	private Integer idPrefix;
	/** 号段起始（含） */
	private Long start;
	/** 号段结束（含） */
	private Long end;
}
