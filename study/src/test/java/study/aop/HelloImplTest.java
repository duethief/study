package study.aop;

import java.lang.reflect.Proxy;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class HelloImplTest {
	@Test
	public void buildHelloImplWithProxy() {
		Hello proxiedHello = (Hello) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { Hello.class }, new UppercaseHandler(new HelloImpl()));
		String output = proxiedHello.sayHello("worm");
		System.out.println("Output은 다음과 같습니다. : " + output);
		System.out.println("Proxy의 이름은 다음과 같습니다. : " + proxiedHello.getClass().getName());
	}
	
	@Test
	public void pointcutAdvisor() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloImpl());
		
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedName("sayH*");
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercastAdvice()));
		
		Hello proxiedHello = (Hello) pfBean.getObject();
		proxiedHello.sayHello("worm");
		proxiedHello.sayHi("worm");
		proxiedHello.sayThankYou("worm");
	}
}
