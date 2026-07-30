package cn.luim.platform.uaa.repository;

import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.platform.uaa.beans.entity.ClientDO;
import cn.luim.platform.uaa.mapper.ClientMapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class ClientRepository extends ServiceImpl<ClientMapper, ClientDO> {

	private static final Logger logger = LoggerFactory.getLogger(ClientRepository.class);

	public boolean clientNameUnique(String clientName) {
		if (StringUtil.isBlank(clientName)) {
			return false;
		}

		return this.lambdaQuery()
			.eq(ClientDO::getClientName, clientName)
			.exists();
	}

	public ClientDO findByClientId(String clientId) {
		return this.lambdaQuery().eq(ClientDO::getClientId, clientId).one();
	}

	@Override
	public boolean save(ClientDO clientDO) {
		if (null == clientDO) {
			logger.warn("[ClientRepository#save] Client save attempted with null object");
			return false;
		}

		boolean saveSuccess = super.save(clientDO);
		if (saveSuccess) {
			logger.info("[ClientRepository#save] Client saved successfully, clientId: {}, clientName: {}", clientDO.getClientId(), clientDO.getClientName());
		}

		return saveSuccess;
	}
}
