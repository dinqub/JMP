import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task5WithMemoryLick {

	public static void main(String[] args) throws IOException, InterruptedException
	{
		// Open the file
		FileInputStream fstream = new FileInputStream("Task5.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;		
		List<String> array = new ArrayList<String>();
		//Read File Line By Line
		while ((strLine = br.readLine()) != null) 
		{
			  array.add(strLine.substring(0,3));		  
		}
 		//Close the input stream
		br.close();
	}
}