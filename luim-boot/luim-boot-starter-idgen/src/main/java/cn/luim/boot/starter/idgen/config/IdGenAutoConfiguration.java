package cn.luim.boot.starter.idgen.config;

import cn.luim.boot.starter.idgen.SegmentBuffer;
import cn.luim.boot.starter.idgen.dubbo.IdGenRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yang.lu
 */
@Configuration
public class IdGenAutoConfiguration {

	@DubboReference
	private IdGenRpcService idGenRpcService;

	@Bean
	public SegmentBuffer segmentBuffer(IdGenRpcService idGenRpcService) {
		return new SegmentBuffer(idGenRpcService);
	}
}
