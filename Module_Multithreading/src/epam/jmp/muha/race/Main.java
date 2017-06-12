package epam.jmp.muha.race;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main 
{
	public static void main(String[] args) throws InterruptedException
	{
		 ExecutorService e = Executors.newFixedThreadPool(10);
	     FinishLine finish = new FinishLine();
	     Random rand = new Random();	 
	     List<Future> cars = new ArrayList<Future>();
	     for (int i = 0; i<10 ; i++)
	     {
	    	 String strI = "Car " + Integer.toString(i+1);
	         System.out.println("Creating a car with Name = " + strI);
	         int n = rand.nextInt(100) + 1;
	         Car car = new Car(strI, n, finish);	    
	         Thread thread = new Thread(car);
	         cars.add(e.submit(thread));
	     }     
	   
	     Thread.sleep(5000);
	     int disCar = rand.nextInt(cars.size()-1);	    
	     cars.get(disCar).cancel(true);
	
	     e.shutdown();
	     e.awaitTermination(60000, TimeUnit.SECONDS); 
		     
	     finish.print();
	}

}
