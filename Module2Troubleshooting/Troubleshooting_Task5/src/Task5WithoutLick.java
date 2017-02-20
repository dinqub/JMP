import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task5WithoutLick {

	public static void main(String[] args) throws IOException
	{
		// Open the file
		FileInputStream fstream = new FileInputStream("Task5.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;		
		List<String> array = new ArrayList<String>();
		//I fix memoryLick by create new Sting for 3 char from even line
		//MemoryLick was because string after method substring  show only 3 char
		// but contain whole line
		while ((strLine = br.readLine()) != null) 
		{
			  array.add(new String(strLine.substring(0,3)));		  
		}
 		//Close the input stream
		br.close();
	}
}