package cn.luim.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yang.lu
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayBootstrap {

	public static void main(String[] args) {
		SpringApplication.run(GatewayBootstrap.class, args);
	}
}
