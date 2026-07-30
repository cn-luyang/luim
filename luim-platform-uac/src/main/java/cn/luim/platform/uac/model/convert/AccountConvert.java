package cn.luim.platform.uac.model.convert;

import cn.luim.platform.uac.model.dto.AccountDetailDTO;
import cn.luim.platform.uac.model.entity.AccountDO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountConvert {


	AccountDetailDTO buildAccountDetailDTO(AccountDO accountDO);
}
