package epam.jmp.muha;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader 
{
      /**
     * Path to the file on the disk.
     */
    private String path;

    public MyClassLoader(ClassLoader parent, String path) 
    {
    	super(parent);
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException 
    {
              Path pathClass = Paths.get(path);
        byte[] classData = null;
        try 
        {
            classData = Files.readAllBytes(pathClass);
        } 
        catch (IOException e) 
        {
                    throw new ClassNotFoundException();
        }
        Class<?> loadedClass = defineClass(name, classData, 0, classData.length);
        return loadedClass;
    }
}