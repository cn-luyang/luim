package cn.luim.platform.uac.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "t_user", autoResultMap = true)
public class UserDO {

	@TableId
	private Long id;
	private String userId;
	private String cnName;
}
