package epam.jmp.muha;

import java.util.ArrayList;
import java.util.List;

import javassist.CtClass;
import javassist.CtField;

public class Metaspace {
	static javassist.ClassPool cp = javassist.ClassPool.getDefault();

	public static void main(String[] args) throws Exception{
		List<Class> classList = new ArrayList<Class>();
		for (int i = 0; ; i++) 
		{ 			
			CtClass generatedClass = cp.makeClass("epam.jmp.muha.demo.Generated" + i);
			CtField f = new CtField(CtClass.doubleType, "arr", generatedClass);
			generatedClass.addField(f);
			Class c = generatedClass.toClass();
			classList.add(c);
		}
	}
}