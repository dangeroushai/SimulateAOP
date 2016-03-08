package app;

import java.lang.reflect.Method;

/**
 * 增强实现类
 * 
 * @author manshu
 * 
 */
public class MyAdvice implements Advice {
	long beginTime = 0;
	long endTime = 0;

	public void beforeMethod(Method method) {
		System.out.println("执行前置增强方法");
		beginTime = System.currentTimeMillis();
	}

	public void afterMethod(Method method) {
		System.out.println("执行后置增强方法");
		endTime = System.currentTimeMillis();

		System.out.println(method.getName() + " Running time of "
				+ (endTime - beginTime));
	}
}
