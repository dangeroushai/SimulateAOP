package app;

import java.io.InputStream;

public class Test {

	public static void main(String[] args) {
		// 获取配置文件的文件流
		InputStream is = Test.class.getResourceAsStream("config.properties");
		// 利用Bean工厂创建配置文件中配置的类对象
		Object bean = new BeanFactory(is).getBean("xxx");
		System.out.println(bean.getClass().getName());
		bean.hashCode();
	}
}
