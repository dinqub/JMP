package epam.jmp.muha.race;

import org.apache.log4j.Logger;

public class Car implements Runnable {
	private static final long MAX_DISTANCE = 10000;
	Logger log = Logger.getLogger(getClass());
	private long friction;
	private long distance;
	private String name;
	private FinishLine finish;

	public Car(String name, long friction, FinishLine finish)
	{
		this.name = name;
		this.friction = 100;
		this.finish = finish;
	}

	@Override
	public void run() {
		try {
			while (distance < MAX_DISTANCE) 
			{
				Thread.sleep(friction);
				distance += 100;
				log.info(name + " " + distance);
			}
			this.finish.arrive(this);
		} 
		catch (InterruptedException e)
		{
			log.error(e);
			System.out.println("Disqualification " + this);
			return;
		}
	}

	@Override
	public String toString() {
		return "Car [name=" + name + "]";
	}
}