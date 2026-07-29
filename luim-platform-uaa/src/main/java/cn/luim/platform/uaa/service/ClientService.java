package cn.luim.platform.uaa.service;

import cn.luim.platform.uaa.model.command.ClientCreateCommand;
import cn.luim.platform.uaa.model.dto.ClientCreateDTO;
import cn.luim.platform.uaa.model.dto.ClientDetailDTO;

public interface ClientService {

	ClientCreateDTO create(ClientCreateCommand command);

	ClientDetailDTO getDetail(String clientId);
}
