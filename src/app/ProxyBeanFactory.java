package app;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 生成代理的工厂类
 * 
 * @author manshu
 * 
 */
public class ProxyBeanFactory {
	// 增强对象
	private Advice advice;

	// 目标对象
	private Object target;

	/**
	 * 
	 * 获取目标对象的代理对象
	 * 
	 * @return
	 */
	public Object getProxy() {
		Object proxy = Proxy.newProxyInstance(target.getClass()
				.getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {
					// 执行代理对象的任何方法时都将被替换为执行如下invoke方法
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						// 前置增强方法
						advice.beforeMethod(method);
						// 目标方法
						Object retVal = method.invoke(target, args);
						// 后置增强方法
						advice.afterMethod(method);
						// 返回目标方法执行结果，代理对象的方法返回值必须与目标对象的方法返回值相同
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
