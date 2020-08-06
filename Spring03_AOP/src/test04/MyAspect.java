package test04;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//해당클래스가 Aspect 역할을 하게끔 만들어주는 어노테이션
@Aspect
public class MyAspect {
	
	//target 객체를 호출하기 전에
	//Before어노테이션이 추가된 메서드를 실행
	@Before("execution(public * *(..))")
	public void before(JoinPoint join) {
		System.out.println("출석카드를 찍는다");
	}
	
	//target 객체가 예외를 반환한 뒤에
	//실행되는 메서드
	
	@AfterThrowing("execution(public * *(..))")
	public void throwing(JoinPoint join) {
		System.out.println("쉬는날 이었다");
	}
	
	//Before, AfterThrowing,after과는 달리 어노테이션에 pointcut과 결과를 반환받을 변수명
	//두가지를 지정해 줘야한다
	//pointcut="",returning="" 형태로 어노테이션을 작성해준다
	@AfterReturning(pointcut = "execution(public * *(..))", returning = "returnVal")
	public void returning(JoinPoint join,Object returnVal) {
		
		System.out.println(returnVal+"공부하는 날이었다");
		
	}
	
	//타깃객체의 메서드를 실행하고,결과값을 반환하기 직전에 실행
	//즉 return값을 가지고오지 않는다
	@After("execution(public * *(..))")
	public void after(JoinPoint join) {
		System.out.println("집에간다");
	}
	//@before >@after>afterReturning
			//@afterthrowing

}
