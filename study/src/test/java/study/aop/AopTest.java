package study.aop;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Method;

import org.junit.Test;

public class AopTest {
	@Test
	public void invokeMethod() throws Exception {
		String name = "worm";
		
		Method lengthMethod = String.class.getMethod("length");
		assertThat((int) lengthMethod.invoke(name), is(4));
	}
}
