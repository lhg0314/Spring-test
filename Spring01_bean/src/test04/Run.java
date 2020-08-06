package test04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {
	
	public static void main(String[] args) {
		ApplicationContext factory=new ClassPathXmlApplicationContext("test04/applicationContext.xml");
		
		BeanTest bean=factory.getBean("beanTest",BeanTest.class);
	}

}
