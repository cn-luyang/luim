package cn.luim.platform.api.uac.model.request;

import cn.luim.platform.api.uac.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class AccountAuthRequest implements Serializable {

	@Serial
	private static final long serialVersionUID = -315296973381422718L;

	String account;
	String credential;
	AccountType accountType;
}
