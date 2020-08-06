package test01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {
	public static void main(String[] args) {
		
		//ApplicationContext가 생성되는 시점에 
		//applicationContext.xml을 참조하도록
		//생성자의 매개변수에 xml파일의 경로를입력
		//applicationcontext는 생성과 동시에 
		//applicationContext.xml파일에서 설정한 일들(객체상성 의존성 주입 등등)을 처리한다
		
		ApplicationContext factory= new ClassPathXmlApplicationContext("test01/applicationContext.xml");
		
		//ApplicationContext로 부터 해당 아이디를 가진 Bean을 반환받음
		Address lee=(Address) factory.getBean("lee");
//		System.out.println(lee);
		Address lee2=(Address) factory.getBean("lee2");
		System.out.println(lee2);
	}

}
