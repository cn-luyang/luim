package cn.luim.boot.starter.redisson.helper;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import java.time.Duration;

/**
 * Redisson 助手类
 *
 * @author yang.lu
 */
public record RedissonHelper(RedissonClient redissonClient) {

	public <T> void setString(String key, T value) {
		redissonClient.getBucket(key).set(value);
	}

	public <T> void setString(String key, T value, Duration duration) {
		this.redissonClient.getBucket(key).set(value, duration);
	}

	public <T> T getString(String key) {
		RBucket<T> bucket = this.redissonClient.getBucket(key);
		return bucket.get();
	}
}
