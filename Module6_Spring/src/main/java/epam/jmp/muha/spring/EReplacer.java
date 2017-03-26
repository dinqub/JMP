package epam.jmp.muha.spring;
import java.lang.reflect.Method;
import org.springframework.beans.factory.support.MethodReplacer;

public class EReplacer implements MethodReplacer
{
  public Object reimplement(Object o, Method m, Object[] a) throws Throwable 
  {    
	String espaniolGreeting = "Hola senior "+a[0]; 
    return espaniolGreeting;
  }
}