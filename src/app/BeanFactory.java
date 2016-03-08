package app;

import java.io.InputStream;
import java.util.Properties;

/**
 * ģ��Spring��IOC������ֻ�ṩ����bean���ܣ���������ע�룩
 * 
 * @author manshu
 * 
 */
public class BeanFactory {

	Properties properties = new Properties();

	/**
	 * ɨ�������ļ�
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
		// ��ȡȫ����
		String className = properties.getProperty(name);
		Object bean = null;

		try {
			// �����ȡ����Ϊname�Ķ���
			Class clazz = Class.forName(className);
			bean = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (bean instanceof ProxyBeanFactory) {
			Object proxy = null;
			ProxyBeanFactory proxyBeanFactory = (ProxyBeanFactory) bean;
			try {
				// ��������ǿ����
				Advice advice = (Advice) Class.forName(
						properties.getProperty(name + ".advice")).newInstance();
				// ������Ŀ�����
				Object target = Class.forName(
						properties.getProperty(name + ".target")).newInstance();
				// ����Ŀ�����Ĵ������,��ֲ����ǿ����,ʵ��AOP
				proxyBeanFactory.setAdvice(advice);
				proxyBeanFactory.setTarget(target);
				proxy = proxyBeanFactory.getProxy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ���Ŀ�걻spring�����򷵻�Ŀ��Ĵ������
			return proxy;
		}
		// ���û�б�spring����ֱ�ӷ���Ŀ��
		return bean;
	}
}
