public class Task1 {
			 
	    public static void main(String[] args)
	    {
	        Object obj1 = new Object();
	        Object obj2 = new Object();
	        	 
	        Thread crunchifyThread1 = new Thread(new CircleThread(obj1, obj2), "CircleThread1");
	        Thread crunchifyThread2 = new Thread(new CircleThread(obj2, obj1), "CircleThread2");
	 
	        crunchifyThread1.start();
            try 
            {
                Thread.sleep(2000);
            } 
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
	        crunchifyThread2.start();
	    }
	}