package cn.luim.platform.uaa.beans.convert;

import cn.luim.platform.uaa.beans.command.ClientCreateCommand;
import cn.luim.platform.uaa.beans.dto.ClientDetailDTO;
import cn.luim.platform.uaa.beans.entity.ClientDO;
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
