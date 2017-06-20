package epam.jmp.muha;
public class Heap 
{
 public static void main(String[] args) 
 {
	 try 
	 {
	   StringBuilder stringBase = new StringBuilder("SuperFail");
		
	   for (int i = 0; i < 1000; i++)
	   {		  
		   stringBase.append(stringBase);		 
	   }
	 }
	 catch (Exception e) 
	 {
		System.out.println("fall");
	 }
	 
 }
}
