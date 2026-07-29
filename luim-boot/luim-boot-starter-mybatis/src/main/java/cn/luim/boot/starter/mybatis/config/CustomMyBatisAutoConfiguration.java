package cn.luim.boot.starter.mybatis.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * MyBatis 自动配置
 *
 * @author yang.lu
 */
@AutoConfiguration
@PropertySource(value = "classpath:mybatis-plus.properties", encoding = "UTF-8")
public class CustomMyBatisAutoConfiguration {
}

