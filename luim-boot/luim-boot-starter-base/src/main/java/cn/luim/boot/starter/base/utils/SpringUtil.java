package cn.luim.boot.starter.base.utils;

import cn.luim.boot.starter.base.exception.UtilException;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Spring 容器工具类
 *
 * @author yang.lu
 */
@Component
public class SpringUtil implements BeanFactoryPostProcessor, ApplicationContextAware {

	private static ConfigurableListableBeanFactory beanFactory;
	private static ApplicationContext applicationContext;

	@Override
	public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
		SpringUtil.beanFactory = beanFactory;
	}

	@Override
	public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}

	/**
	 * 获取可用的 BeanFactory
	 *
	 * @return ListableBeanFactory 实例
	 * @throws UtilException 当 BeanFactory 未初始化时抛出
	 */
	public static ListableBeanFactory getBeanFactory() {
		if (beanFactory != null) {
			return beanFactory;
		}

		if (applicationContext instanceof ListableBeanFactory lbf) {
			return lbf;
		}

		throw new UtilException("No BeanFactory or ApplicationContext injected!");
	}

	/**
	 * 获取 ConfigurableListableBeanFactory
	 *
	 * @return ConfigurableListableBeanFactory 实例
	 * @throws UtilException 当无法获取时抛出
	 */
	public static ConfigurableListableBeanFactory getConfigurableBeanFactory() {
		if (beanFactory != null) {
			return beanFactory;
		}

		if (applicationContext instanceof ConfigurableApplicationContext cac) {
			return cac.getBeanFactory();
		}

		throw new UtilException("No ConfigurableListableBeanFactory available!");
	}

	/**
	 * 按类型获取所有 Bean
	 *
	 * @param type Bean 类型
	 * @param <T>  泛型
	 * @return Bean 名称与实例映射，可能为空但非 null
	 * @throws UtilException 当 BeanFactory 不可用时抛出
	 */
	public static <T> Map<String, T> getBeansOfType(final Class<T> type) {
		return getBeanFactory().getBeansOfType(type);
	}

	/**
	 * 按类型获取单个 Bean
	 *
	 * @param clazz Bean 类型
	 * @param <T>   泛型
	 * @return Bean 实例
	 * @throws UtilException 当 Bean 不存在或 BeanFactory 不可用时抛出
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getBeanFactory().getBean(clazz);
	}
}
