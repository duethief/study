package study.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class UppercastAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("method pre in : " + invocation.getMethod().getName());
		Object ret = invocation.proceed();
		System.out.println("method after : " + invocation.getMethod().getName());
		return ret;
	}

}
