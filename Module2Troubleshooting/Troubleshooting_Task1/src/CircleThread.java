public class CircleThread implements Runnable
	{
	    private Object obj1;
	    private Object obj2;
	 
	    public CircleThread(Object obj1, Object obj2) 
	    {
	        this.obj1 = obj1;
	        this.obj2 = obj2;
	    }
	 
	    @Override
	    public void run() {
	        String name = Thread.currentThread().getName();
	        synchronized (obj1)
	        {
	            System.out.println(name + " lock on : " + obj1);
	            try 
	            {
	                Thread.sleep(4000);
	            } 
	            catch (InterruptedException e)
	            {
	                e.printStackTrace();
	            }	 
	            synchronized (obj2)
	            {
	                System.out.println(name + " lock on : " + obj2);
	            }
	            System.out.println(name + " released lock on : " + obj2);
	        }
	        System.out.println(name + " released lock on : " + obj1);
	    }
}
