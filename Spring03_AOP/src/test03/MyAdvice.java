package test03;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cglib.proxy.MethodProxy;

@Aspect
public class MyAdvice{

	public void before(JoinPoint join) {
		
		//JointPoint타깃객체에 다한 정보를 담고있다
		//spring Aop를 사용하기 위해서는 반드시 매개변수로 넣어줘야하는 
		//객체입니다
		
		//클래스명
		System.out.println(join.getTarget().getClass());
		//메서드 이름
		System.out.println(join.getSignature().getName());
		//대상 객체 메서드의 정보
		System.out.println(join);
		//매서드 매개변수
		System.out.println(join.getArgs());
		
		System.out.println("출석카드");
	}
	
	public void after(JoinPoint join) {
		System.out.println("집에간다");
	}

	

	

}
