package cn.luim.platform.uaa.service.impl;

import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.boot.starter.base.utils.id.IdUtil;
import cn.luim.platform.uaa.common.enums.ErrorCode;
import cn.luim.platform.uaa.model.command.ClientCreateCommand;
import cn.luim.platform.uaa.model.convert.ClientConvert;
import cn.luim.platform.uaa.model.dto.ClientCreateDTO;
import cn.luim.platform.uaa.model.dto.ClientDetailDTO;
import cn.luim.platform.uaa.model.entity.ClientDO;
import cn.luim.platform.uaa.repository.ClientRepository;
import cn.luim.platform.uaa.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

	private final PasswordEncoder passwordEncoder;
	private final ClientRepository clientRepository;
	private final ClientConvert clientConvert;

	@Override
	public ClientCreateDTO create(ClientCreateCommand command) {

		// 校验客户端名称是否唯一
		boolean isNameUnique = clientRepository.clientNameUnique(command.getClientName());
		ErrorCode.CLIENT_EXISTS.isFalse(isNameUnique);

		// 生成客户端密钥
		String rawSecret = IdUtil.simpleUUID();
		String encodedSecret = passwordEncoder.encode(rawSecret);

		// 构建并保存实体
		ClientDO clientDO = clientConvert.buildEntity(command, encodedSecret);
		clientRepository.save(clientDO);

		return ClientCreateDTO.build(clientDO.getClientId(), rawSecret);
	}

	@Override
	public ClientDetailDTO getDetail(String clientId) {
		if (StringUtil.isBlank(clientId)) {
			return null;
		}

		ClientDO clientDO = clientRepository.findByClientId(clientId);
		return clientConvert.buildClientDetailDTO(clientDO);
	}
}
