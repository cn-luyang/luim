package cn.luim.platform.api.uac.model.response;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yang.lu
 */
@Data
public class GetUserInfoResponse implements Serializable {

	@Serial
	private static final long serialVersionUID = 4808504904401015433L;

	private String userId;
	private String cnName;
}
