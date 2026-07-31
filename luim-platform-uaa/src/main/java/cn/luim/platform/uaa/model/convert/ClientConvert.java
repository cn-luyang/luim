package cn.luim.platform.uaa.model.convert;

import cn.luim.boot.starter.base.utils.id.IdUtil;
import cn.luim.platform.uaa.model.command.ClientCreateCommand;
import cn.luim.platform.uaa.model.dto.ClientDetailDTO;
import cn.luim.platform.uaa.model.entity.ClientDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 客户端相关对象转换
 *
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {
		IdUtil.class
	})
public interface ClientConvert {

	@Mapping(target = "clientId", expression = "java(\"cli_\" + IdUtil.nanoId(16))")
	ClientDO toEntity(ClientCreateCommand command, String clientSecret);

	ClientDetailDTO toClientDetailDTO(ClientDO clientDO);
}
