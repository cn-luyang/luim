package cn.luim.platform.uaa;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yang.lu
 */
@EnableDubbo
@SpringBootApplication
public class UaaBootstrap {

	public static void main(String[] args) {
		SpringApplication.run(UaaBootstrap.class, args);
	}
}
