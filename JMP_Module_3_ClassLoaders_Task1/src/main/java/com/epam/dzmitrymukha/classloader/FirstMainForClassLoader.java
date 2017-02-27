package com.epam.dzmitrymukha.classloader;
public class FirstMainForClassLoader
{ 
     private static String className = "com.epam.mentoring.lessone.Semaphore";
    
     public static void main(String[] args) throws Exception 
     {
         String path2class;
         if (args.length != 0 && args[0].length()!=0 )
         {
        	 path2class = args[0];
         }
         else
         {
             System.out.println("Please, execute program in the following format: java FirstMainForClassLoader disk:\\path\\class.");
             return;
         }
         System.out.println("Loading class from '" + path2class + "' directory");
         ClassLoader classLoader = new MyClassLoader(path2class);
         Class<?> loadedclass = classLoader.loadClass(className);
         Object instance = loadedclass.newInstance();
         loadedclass.getMethod("lever").invoke(instance);
     }
 }