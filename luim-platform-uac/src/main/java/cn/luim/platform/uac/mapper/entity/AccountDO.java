package cn.luim.platform.uac.mapper.entity;

import cn.luim.platform.api.uac.enums.AccountType;
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
	private AccountType accountType;
}
