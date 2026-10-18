package cn.luim.platform.uaa.repository;

import cn.luim.boot.starter.base.utils.ObjectUtil;
import cn.luim.platform.uaa.mapper.ClientMapper;
import cn.luim.platform.uaa.mapper.entity.ClientDO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class ClientRepository extends ServiceImpl<ClientMapper, ClientDO> {

	private static final Logger logger = LoggerFactory.getLogger(ClientRepository.class);

	public boolean isClientNameExist(String clientName) {
		Assert.hasText(clientName, "缺失查询参数");
		return this.lambdaQuery()
			.eq(ClientDO::getClientName, clientName)
			.exists();
	}

	public ClientDO findByClientId(String clientId) {
		Assert.hasText(clientId, "缺失查询参数");
		return this.lambdaQuery()
			.eq(ClientDO::getClientId, clientId)
			.oneOpt()
			.orElse(null);
	}

	@Override
	public boolean save(ClientDO clientDO) {
		if (ObjectUtil.isNull(clientDO)) {
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
