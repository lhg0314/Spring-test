package test02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {
	
	public static void main(String[] args) {
		
		//Dev woman=new WomanProxy();
		//Dev man=new MyAdvice();
		ApplicationContext factory=new ClassPathXmlApplicationContext("test02/applicationContext.xml");
		
		Dev woman=(Dev) factory.getBean("woman");
		Dev man=(Dev) factory.getBean("man");
		
		System.out.println("여학생 입장");
		woman.classWork();
		System.out.println("--------");
		System.out.println("남학생 입장");
		man.classWork();
		
	}

}
