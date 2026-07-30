package cn.luim.platform.uac.model.dto;

import cn.luim.platform.api.uac.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class AccountDetailDTO {

	private String accountId;
	private String userId;
	private String account;
	private AccountType accountType;
}
