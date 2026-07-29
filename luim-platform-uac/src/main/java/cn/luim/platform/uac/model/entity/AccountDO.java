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
@TableName(value = "t_account", autoResultMap = true)
public class AccountDO {

	@TableId
	private Long id;
	private String userId;
	private String account;
}
