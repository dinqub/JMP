package epam.jmp.muha.spring.definition;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CustomPostProcessor implements BeanPostProcessor
{

	public Object postProcessAfterInitialization(Object o, String s) throws BeansException
	{
		if(s.equals("F"))
		{
			System.out.println("Bean "+s+" End initialization");
		}
		return o; 
	}

	public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
		if(s.equals("F"))
		{
			System.out.println("Bean "+s+" Start initialization");
		}
		return o;
	}

}
