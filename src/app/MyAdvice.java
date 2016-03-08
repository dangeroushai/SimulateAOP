package app;

import java.lang.reflect.Method;

/**
 * ��ǿʵ����
 * 
 * @author manshu
 * 
 */
public class MyAdvice implements Advice {
	long beginTime = 0;
	long endTime = 0;

	public void beforeMethod(Method method) {
		System.out.println("ִ��ǰ����ǿ����");
		beginTime = System.currentTimeMillis();
	}

	public void afterMethod(Method method) {
		System.out.println("ִ�к�����ǿ����");
		endTime = System.currentTimeMillis();

		System.out.println(method.getName() + " Running time of "
				+ (endTime - beginTime));
	}
}
