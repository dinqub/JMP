public class Task4 {
			 
	    public static void main(String[] args)
	    {
	        Object obj1 = new Object();
	        	 
	        Thread thread1 = new Thread(new BottleThread(obj1, 40000), "MainBottleThread");
	        Thread thread2 = new Thread(new BottleThread(obj1, 20000), "BottleThread1");
	        Thread thread3 = new Thread(new BottleThread(obj1, 20000), "BottleThread2");
	        Thread thread4 = new Thread(new BottleThread(obj1, 20000), "BottleThread3");
	        Thread thread5 = new Thread(new BottleThread(obj1, 20000), "BottleThread4");
	 
	        thread1.start();
            try 
            {
                Thread.sleep(2000);
            } 
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
	        thread2.start();
	        thread3.start();
	        thread4.start();
	        thread5.start();
	    }
	}