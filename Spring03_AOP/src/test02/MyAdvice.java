package test02;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodProxy;

public class MyAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object object=null;
		System.out.println("출석카드를 찍는다");
		
		try {
			object=invocation.proceed();
		} catch (Exception e) {
			System.out.println("쉬는날이었다");
		}finally {
			System.out.println("집에간다");
		}
		
		return null;
	}

	

	

}
