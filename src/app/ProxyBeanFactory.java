package app;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ���ɴ���Ĺ�����
 * 
 * @author manshu
 * 
 */
public class ProxyBeanFactory {
	// ��ǿ����
	private Advice advice;

	// Ŀ�����
	private Object target;

	/**
	 * 
	 * ��ȡĿ�����Ĵ������
	 * 
	 * @return
	 */
	public Object getProxy() {
		Object proxy = Proxy.newProxyInstance(target.getClass()
				.getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {
					// ִ�д��������κη���ʱ�������滻Ϊִ������invoke����
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						// ǰ����ǿ����
						advice.beforeMethod(method);
						// Ŀ�귽��
						Object retVal = method.invoke(target, args);
						// ������ǿ����
						advice.afterMethod(method);
						// ����Ŀ�귽��ִ�н�����������ķ�������ֵ������Ŀ�����ķ�������ֵ��ͬ
						return retVal;
					}
				});
		return proxy;

	}

	public Advice getAdvice() {
		return advice;
	}

	public void setAdvice(Advice advice) {
		this.advice = advice;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

}
