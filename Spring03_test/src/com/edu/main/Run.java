package com.edu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edu.entity.Rectangle;
import com.edu.entity.Triangle;

public class Run {
	public static void main(String[] args) {
		ApplicationContext factory=new ClassPathXmlApplicationContext("com/edu/main/applicationContext.xml");
		Triangle t=(Triangle) factory.getBean("tri");
		Rectangle r=(Rectangle) factory.getBean("rect");
		
		//Type
		//메서드의 시그니처 정보와 필드변수 정보를 가지고 있습니다
		//상속을 통해거 Type을 공유한다는것은
		//접근 가능한 메서드의 시그니처와 필드 변수도 같이 공유한다는 것
		//다형성이 가능해지는 이유입니다
		
		r.viewSize();
		
		System.out.println("------------------");
		
		t.viewSize();
		
	}

}
