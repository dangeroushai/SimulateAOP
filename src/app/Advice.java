package app;

import java.lang.reflect.Method;

/**
 * ��ǿ�ӿ�
 * 
 * @author manshu
 * 
 */
public interface Advice {

	/**
	 * ǰ����ǿ����
	 */
	void beforeMethod(Method method);

	/**
	 * ������ǿ����
	 */
	void afterMethod(Method method);

}
