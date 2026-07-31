package cn.luim.platform.uac.model.convert;

import cn.luim.platform.uac.model.dto.AccountDetailDTO;
import cn.luim.platform.uac.model.entity.AccountDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountConvert {


	@Mapping(target = "accountId", source = "id")
	AccountDetailDTO toAccountDetailDTO(AccountDO accountDO);
}
