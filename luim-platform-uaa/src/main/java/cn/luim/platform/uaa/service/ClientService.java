package cn.luim.platform.uaa.service;

import cn.luim.platform.uaa.beans.command.ClientCreateCommand;
import cn.luim.platform.uaa.beans.dto.ClientCreateDTO;
import cn.luim.platform.uaa.beans.dto.ClientDetailDTO;

public interface ClientService {

	ClientCreateDTO create(ClientCreateCommand command);

	ClientDetailDTO getDetail(String clientId);
}
