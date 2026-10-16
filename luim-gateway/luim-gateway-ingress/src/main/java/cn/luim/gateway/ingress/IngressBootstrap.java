package cn.luim.gateway.ingress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yang.lu
 */
@EnableDiscoveryClient
@SpringBootApplication
public class IngressBootstrap {

	public static void main(String[] args) {
		SpringApplication.run(IngressBootstrap.class, args);
	}
}
