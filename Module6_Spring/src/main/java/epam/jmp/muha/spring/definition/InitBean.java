package epam.jmp.muha.spring.definition;

import org.springframework.beans.factory.InitializingBean;

public class InitBean implements InitializingBean 
{

	public void afterPropertiesSet() throws Exception
	{
		System.out.println("Init Bean: After properties set");
		
	}

}
