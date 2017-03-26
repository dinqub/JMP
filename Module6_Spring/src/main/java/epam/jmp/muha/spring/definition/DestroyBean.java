package epam.jmp.muha.spring.definition;

import org.springframework.beans.factory.DisposableBean;

public class DestroyBean implements DisposableBean 
{
	public void destroy() throws Exception
	{
		System.out.println("Beans was destroyed");
	}

}
