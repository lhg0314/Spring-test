package test04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {
	public static void main(String[] args) {
		
		ApplicationContext factory=new ClassPathXmlApplicationContext("test04/applicationContext.xml");
		
		Dev man=(Dev) factory.getBean("man");
		Dev woman=(Dev) factory.getBean("woman");
		
		man.classWork();
		System.out.println("----------------");
		woman.classWork();
	}

}
