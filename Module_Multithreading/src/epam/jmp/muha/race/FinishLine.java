package epam.jmp.muha.race;

import java.util.Vector;

public class FinishLine {

    Vector<Car> ranking = new Vector<Car>(10);

    public FinishLine()
    {
    	
    }
    
    public synchronized void arrive(Car car)
    {
        ranking.add(car);
    }

    public void print()
    {
    	System.out.println("Winner is : " + ranking.get(0));
        for (int i = 1; i < ranking.size() ; i++)
        {
        	System.out.println("Place " + (i + 1)+ " is : " + ranking.get(i));
        }
    }
}
