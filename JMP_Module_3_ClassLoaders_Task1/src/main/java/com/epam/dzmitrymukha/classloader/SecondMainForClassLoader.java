package com.epam.dzmitrymukha.classloader;

import java.util.Scanner;

public class SecondMainForClassLoader
{ 
     private static String className = "com.epam.mentoring.lessone.Semaphore";
  
     public static void main(String[] args) throws Exception 
     {
         String path2class = new String();
/*         if (args.length != 0 && args[0].length()!=0 )
         {
        	 path2class = args[0];
         }
         else
         {
             System.out.println("Please, execute program in the following format: java SrcondMainForClassLoader disk:\\path\\class.");
             return;
         } */
         Scanner input = new Scanner(System.in);
         String nextStep = null;
         path2class = "e:\\Semaphore.class";
         do 
         {
        	 if (nextStep != null && !nextStep.equals(""))
        	 {
        		 path2class = nextStep;
        	 }
             System.out.println("Loading class from '" + path2class + "' directory.");
             ClassLoader classLoader = new MyClassLoader(MyClassLoader.class.getClassLoader(), path2class);
             Class<?> loadedclass = null;
             try
             {
            	 loadedclass = classLoader.loadClass(className);
             }
             catch (ClassNotFoundException e)
             {
            	 System.out.println("Please, check your path");
            	 nextStep = choseYourNextStep(input);
            	 continue;
             }
             Object instance = loadedclass.newInstance();
             loadedclass.getMethod("lever").invoke(instance);

             nextStep = choseYourNextStep(input);
         } 
         while (!nextStep.equals("exit"));
     }
     
     public static String choseYourNextStep( Scanner input)
     {
         System.out.println("Please, type full addres in disk:\\path\\class format for load this class from other place");
         System.out.println("Please, type enter - for reload '.class' file from the same place");
         System.out.println("Please, type exit  - for exit");
         return input.nextLine();
     }
 }