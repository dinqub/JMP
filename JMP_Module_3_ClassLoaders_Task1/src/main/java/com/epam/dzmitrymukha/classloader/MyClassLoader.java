package com.epam.dzmitrymukha.classloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class MyClassLoader extends ClassLoader 
{
    final static Logger logger = Logger.getLogger(MyClassLoader.class);

    /**
     * Path to the file on the disk.
     */
    private String path;

    public MyClassLoader(String path) 
    {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException 
    {
        logger.info("Loading in progress...");
        Path pathClass = Paths.get(path);
        byte[] classData = null;
        try 
        {
            classData = Files.readAllBytes(pathClass);
        } 
        catch (IOException e) 
        {
            logger.error("Error: " + e );
            throw new ClassNotFoundException();
        }
        Class<?> loadedClass = defineClass(name, classData, 0, classData.length);
        logger.info("Class '" + loadedClass.getName() + "' is successfully loaded");
        return loadedClass;
    }
}