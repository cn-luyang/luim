package cn.luim.platform.uaa.service;

import cn.luim.platform.uaa.controller.request.ClientCreateRequest;
import cn.luim.platform.uaa.controller.response.ClientCreateResponse;
import cn.luim.platform.uaa.model.dto.ClientDetailDTO;

public interface ClientService {

	ClientCreateResponse create(ClientCreateRequest clientCreateRequest);

	ClientDetailDTO getDetail(String clientId);
}
