package app;

import java.io.InputStream;
import java.util.Properties;

/**
 * 模拟Spring的IOC工厂（只提供创建bean功能，不能依赖注入）
 * 
 * @author manshu
 * 
 */
public class BeanFactory {

	Properties properties = new Properties();

	/**
	 * 扫描配置文件
	 * 
	 * @param is
	 */
	public BeanFactory(InputStream is) {
		try {
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getBean(String name) {
		// 获取全类名
		String className = properties.getProperty(name);
		Object bean = null;

		try {
			// 反射获取类名为name的对象
			Class clazz = Class.forName(className);
			bean = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (bean instanceof ProxyBeanFactory) {
			Object proxy = null;
			ProxyBeanFactory proxyBeanFactory = (ProxyBeanFactory) bean;
			try {
				// 反射获得增强对象
				Advice advice = (Advice) Class.forName(
						properties.getProperty(name + ".advice")).newInstance();
				// 反射获得目标对象
				Object target = Class.forName(
						properties.getProperty(name + ".target")).newInstance();
				// 生成目标对象的代理对象,并植入增强方法,实现AOP
				proxyBeanFactory.setAdvice(advice);
				proxyBeanFactory.setTarget(target);
				proxy = proxyBeanFactory.getProxy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 如果目标被spring管理，则返回目标的代理对象
			return proxy;
		}
		// 如果没有被spring管理，直接返回目标
		return bean;
	}
}
