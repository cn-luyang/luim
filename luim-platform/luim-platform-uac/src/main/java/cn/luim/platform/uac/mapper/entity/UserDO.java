package cn.luim.platform.uac.mapper.entity;

import cn.luim.platform.uac.common.enums.database.UserType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "uac_user", autoResultMap = true)
public class UserDO {

	@TableId("id")
	private Long userId;
	private String realName;
	private String email;
	private UserType userType;
}
