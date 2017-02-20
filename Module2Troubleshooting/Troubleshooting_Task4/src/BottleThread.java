public class BottleThread implements Runnable
	{
	    private Object obj1;
	    private long sleepTime;
	 
	    public BottleThread(Object obj1, long sleepTime) 
	    {
	        this.obj1 = obj1;
	        this.sleepTime = sleepTime;
	    }
	 
	    @Override
	    public void run() {
	        String name = Thread.currentThread().getName();
	        System.out.println(name + " start");
	        synchronized (obj1)
	        {
	            System.out.println(name + " lock on : " + obj1);
	            try 
	            {
	                Thread.sleep(sleepTime);
	            } 
	            catch (InterruptedException e)
	            {
	                e.printStackTrace();
	            }	 
	        }
	        System.out.println(name + " released lock on : " + obj1);
	    }
}
