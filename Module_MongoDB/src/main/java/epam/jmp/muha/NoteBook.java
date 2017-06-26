package epam.jmp.muha;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class NoteBook {
	private static final String EXIT = "exit";
	private static final String YES = "y";

	public static void main(String[] args) throws IOException {

		try {

			/**** Connect to MongoDB ****/
			MongoClient mongo = new MongoClient("localhost", 27017);

			/**** Get database ****/
			DB db = mongo.getDB("testdb");

			/**** Get collection / table from 'testdb' ****/
			DBCollection table = db.getCollection("user");
			Scanner in = new Scanner(System.in);
			String choose = null;
			while (!EXIT.equals(choose))
			{
				System.out.println("Print 1 to view all records," + 
						"\nPrint 2 to insert new," + 
						"\nPrint 3 to find by tag,"+
						"\nPrint 4 to find by tag and note"+
						"\nPrint exit for exit");
				choose = in.nextLine();				
				switch (choose) {
				case "1":
					System.out.println("Show all records");
					/**** Find all ****/
					DBCursor cursorAll = table.find();
	
					boolean existResultAll = cursorAll.hasNext();
					if(!existResultAll)
					{
						System.out.println("Nothing found");
					}
					else
					{
						System.out.println("Result:");
					}
	
					List<DBObject> resultListAll = new ArrayList<DBObject>();
					while (cursorAll.hasNext())
					{
						DBObject dbDocument = cursorAll.next();
						System.out.println(dbDocument);
						resultListAll.add(dbDocument);
					}
					if(existResultAll)
					{
						System.out.println("Do you want remove it (y/n)");
						String result = in.nextLine();	
						if (YES.equals(result))
						{
							for (DBObject dbObject : resultListAll)
							{
								table.remove(dbObject);
							}							
						}
					}	
					break;
				case "2":
					/**** Insert ****/				
					System.out.println("Enter tag:\n");
					String tag = in.nextLine();	
					System.out.println("Enter note:");
					String note = in.nextLine();	
					
					BasicDBObject document = new BasicDBObject();
					document.put("date", new Date());
					document.put("tag", tag);	
					document.put("note", note);
					table.insert(document);
					System.out.println("Inserted" + document);
					break;
				case "3":
					System.out.println("Finded by tag");
					/**** Find and display ****/
					System.out.println("Enter tag:\n");
					String tagName = in.nextLine();	
					BasicDBObject searchTagQuery = new BasicDBObject();
					searchTagQuery.put("tag", tagName);
	
					DBCursor cursorTag = table.find(searchTagQuery);
					boolean existTagResult = cursorTag.hasNext();
					if(!existTagResult)
					{
						System.out.println("Nothing found");
					}
					else
					{
						System.out.println("Result:");
					}
	
					List<DBObject> resultTagList = new ArrayList<DBObject>();
					while (cursorTag.hasNext())
					{
						DBObject dbDocument = cursorTag.next();
						System.out.println(dbDocument);
						resultTagList.add(dbDocument);
					}
					if(existTagResult)
					{
						System.out.println("Do you want remove it (y/n)");
						String result = in.nextLine();	
						if (YES.equals(result))
						{
							for (DBObject dbObject : resultTagList)
							{
								table.remove(dbObject);
							}							
						}
					}
					break;
				case "4":
					System.out.println("Finded by tag and note");
					/**** Find and display ****/
					System.out.println("Enter tag:\n");
					String tagParameter = in.nextLine();	
					System.out.println("Enter note:\n");
					String noteParameter = in.nextLine();	
					
					BasicDBObject searchQuery = new BasicDBObject();
					searchQuery.put("tag", tagParameter);
					searchQuery.put("note",noteParameter);
	
					DBCursor cursor = table.find(searchQuery);
					
					boolean existResult = cursor.hasNext();
					if(!existResult)
					{
						System.out.println("Nothing found");
					}
					else
					{
						System.out.println("Result:");
					}
	
					List<DBObject> resultList = new ArrayList<DBObject>();
					while (cursor.hasNext())
					{
						DBObject dbDocument = cursor.next();
						System.out.println(dbDocument);
						resultList.add(dbDocument);
					}
					if(existResult)
					{
						System.out.println("Do you want remove it (y/n)");
						String result = in.nextLine();	
						if (YES.equals(result))
						{
							for (DBObject dbObject : resultList)
							{
								table.remove(dbObject);
							}							
						}
					}					
					break;
				default:
					break;
				}
			}

			/**** Done ****/
			System.out.println("Done");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}
}
