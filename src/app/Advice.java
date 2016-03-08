package app;

import java.lang.reflect.Method;

/**
 * 增强接口
 * 
 * @author manshu
 * 
 */
public interface Advice {

	/**
	 * 前置增强方法
	 */
	void beforeMethod(Method method);

	/**
	 * 后置增强方法
	 */
	void afterMethod(Method method);

}
