package cn.luim.platform.uaa.repository;

import cn.luim.platform.uaa.beans.entity.TokenDO;
import cn.luim.platform.uaa.mapper.TokenMapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class TokenRepository extends ServiceImpl<TokenMapper, TokenDO> {

	private static final Logger logger = LoggerFactory.getLogger(TokenRepository.class);

	public Long findValidTokenId(String clientId, String userId) {
		return this.lambdaQuery()
			.eq(TokenDO::getClientId, clientId)
			.eq(TokenDO::getUserId, userId)
			.gt(TokenDO::getAccessTokenExpiresTime, LocalDateTime.now())
			.select(TokenDO::getId)
			.oneOpt()
			.map(TokenDO::getId)
			.orElse(null);
	}
}
