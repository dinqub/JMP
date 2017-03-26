package epam.jmp.muha.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
	public static void main(String[] args) 
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");

		A objA = (A) context.getBean("A");
		B objB = (B) context.getBean("B");
		C objC = (C) context.getBean("C");
		E objE = (E) context.getBean("E");
		F objF = (F) context.getBean("F");
		
	/*	System.out.println(objA);
		System.out.println(objB);
		System.out.println(objC);
		System.out.println(objE.sayHello("Dzmitry"));*/
		
		System.out.println(objF);
		((AbstractApplicationContext)context).close();
	}
}
