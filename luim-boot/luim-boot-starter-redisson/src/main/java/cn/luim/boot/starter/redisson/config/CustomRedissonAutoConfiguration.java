package cn.luim.boot.starter.redisson.config;

import cn.luim.boot.starter.redisson.helper.RedissonHelper;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Redisson 自动配置
 *
 * @author yang.lu
 */
@AutoConfiguration
public class CustomRedissonAutoConfiguration {

	@Bean
	public RedissonHelper redissonHelper(RedissonClient redissonClient) {
		return new RedissonHelper(redissonClient);
	}
}
