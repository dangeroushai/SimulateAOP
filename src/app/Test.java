package app;

import java.io.InputStream;

public class Test {

	public static void main(String[] args) {
		// ��ȡ�����ļ����ļ���
		InputStream is = Test.class.getResourceAsStream("config.properties");
		// ����Bean�������������ļ������õ������
		Object bean = new BeanFactory(is).getBean("xxx");
		System.out.println(bean.getClass().getName());
		bean.hashCode();
	}
}
