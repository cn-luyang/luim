package cn.luim.platform.uac.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "t_password", autoResultMap = true)
public class PasswordDO {

	@TableId
	private Long id;
	private String accountId;
	private String passwordHash;
}
