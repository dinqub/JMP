package epam.jmp.muha;

import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;


public class NoteBook {
	public static void main(String[] args) {

		try {

			/**** Connect to MongoDB ****/
			MongoClient mongo = new MongoClient("localhost", 27017);

			/**** Get database ****/
			DB db = mongo.getDB("testdb");

			/**** Get collection / table from 'testdb' ****/
			DBCollection table = db.getCollection("user");

			/**** Insert ****/
			BasicDBObject document = new BasicDBObject();
			document.put("name", "dima");
			document.put("age", 30);
			document.put("createdDate", new Date());
			table.insert(document);

			/**** Find and display ****/
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("name", "dima");

			DBCursor cursor = table.find(searchQuery);

			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

			/**** Update ****/
			BasicDBObject query = new BasicDBObject();
			query.put("name", "dima");

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("name", "dima-updated");

			BasicDBObject updateObj = new BasicDBObject();
			updateObj.put("$set", newDocument);

			table.update(query, updateObj);

			/**** Find and display ****/
			BasicDBObject searchQuery2 
				= new BasicDBObject().append("name", "dima-updated");

			DBCursor cursor2 = table.find(searchQuery2);

			while (cursor2.hasNext()) {
				System.out.println(cursor2.next());
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
