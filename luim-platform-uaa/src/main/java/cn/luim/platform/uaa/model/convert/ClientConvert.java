package cn.luim.platform.uaa.model.convert;

import cn.luim.platform.uaa.model.command.ClientCreateCommand;
import cn.luim.platform.uaa.model.dto.ClientDetailDTO;
import cn.luim.platform.uaa.model.entity.ClientDO;
import org.mapstruct.Mapper;

/**
 * 客户端相关对象转换
 *
 * @author yang.lu
 */
@Mapper(componentModel = "spring")
public interface ClientConvert {

	ClientDO buildEntity(ClientCreateCommand command, String encodedSecret);

	ClientDetailDTO buildClientDetailDTO(ClientDO clientDO);
}
