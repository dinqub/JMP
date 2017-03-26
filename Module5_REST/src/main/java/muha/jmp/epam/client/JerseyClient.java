package muha.jmp.epam.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;

public class JerseyClient {

	public static void main(String[] args) {

		try {
			int checkedUserId = 1;
			checkCreate();
			checkRead(checkedUserId);
			checkUpdate(checkedUserId);
			checkUploadLogo(checkedUserId);
			checkDownloadLogo(checkedUserId);
			checkRead(checkedUserId);
			checkRemove(checkedUserId);

		} catch (Exception e) {

			e.printStackTrace();

		}
		
	}

	private static void checkCreate()
	{
		Client client = Client.create();

		WebResource webResource = client
				.resource("http://localhost:8080/Module5_REST/rest/user/create");

		String input = "<user id=\"1\">"
				+ "<firstName>Mukha</firstName>"
				+ "<lastName>Dzmitry1</lastName>"
				+ "<login>spider</login>"
				+ "<email>dzmitry@epam.com</email>"
				+ "</user>";

		ClientResponse response = webResource.type("application/xml")
				.post(ClientResponse.class, input);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		System.out.println("Output from Server by create Request test.... ");
		String output = response.getEntity(String.class);
		System.out.println(output+"\n");	
	}
	private static void checkRead(int id)
	{
		Client client = Client.create();

		WebResource webResource = client
		   .resource("http://localhost:8080/Module5_REST/rest/user/read/"+id);

		ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		String output = response.getEntity(String.class);

		System.out.println("Output from Server by read rest Request test .... ");
		System.out.println(output+"\n");	
	}
	private static void checkUpdate(int id)
	{
		Client client = Client.create();

		WebResource webResource = client
				.resource("http://localhost:8080/Module5_REST/rest/user/update");

		String input = "{\"login\": \"spider\","+
				"\"id\": \""+id+"\","+
			  "\"firstName\": \"Mukha\","+
			  "\"email\": \"dzmitry@epam.com\","+
			  "\"lastName\": \"DzmitryUpdateTest\"}";

		ClientResponse response = webResource.type("application/json")
				.post(ClientResponse.class, input);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		System.out.println("Output from Server by update Request test.... ");
		String output = response.getEntity(String.class);
		System.out.println(output+"\n");		
	}
	private static void checkUploadLogo(int id) throws Exception
	{
        File file = new File("c:/Users/Дмитрий/Documents/Больничный.jpg") ;
        
        DefaultClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getClasses().add(MultiPartWriter.class);
        Client client = Client.create(clientConfig);
        
        FormDataMultiPart multiPart = new FormDataMultiPart();
        multiPart.bodyPart(new FileDataBodyPart("file", file, MediaType.APPLICATION_OCTET_STREAM_TYPE));

		WebResource webResource = client
				.resource("http://localhost:8080/Module5_REST/rest/user/updateLogo/"+id);
		        	
		ClientResponse response = webResource.type(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.TEXT_PLAIN)
                .post(ClientResponse.class, multiPart);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		System.out.println("Output from Server by updateLogo Request test.... ");
		String output = response.getEntity(String.class);
		System.out.println(output+"\n");				
	}
	
	private static void checkDownloadLogo(int id) throws Exception
	{
		Client client = Client.create();

		WebResource webResource = client
		   .resource("http://localhost:8080/Module5_REST/rest/user/downloadLogo/"+id);

		ClientResponse response = webResource.accept("image/png")
                   .get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		String output = response.getEntity(String.class);

		System.out.println("Output from Server by downloadLogo rest Request test .... ");		
	}
	private static void checkRemove(int id)
	{
		Client client = Client.create();

		WebResource webResource = client
		   .resource("http://localhost:8080/Module5_REST/rest/user/delete/"+id);

		ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		String output = response.getEntity(String.class);

		System.out.println("Output from Server by delete rest Request test .... ");
		System.out.println(output+"\n");	
	}

	public static void executeMultiPartRequest(String urlString, File file, String fileName, String fileDescription) throws Exception 
	    {
		File inFile = new File("C:\\Users\\Дмитрий\\Documents\\Больничный.jpg");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(inFile);
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			
			// server back-end URL
			HttpPost httppost = new HttpPost(urlString);
			MultipartEntity entity = new MultipartEntity();
			// set the file input stream and file name as arguments
			//entity.addPart("file", new InputStreamBody(fis, inFile.getName()));
			httppost.setEntity(entity);
			// execute the request
			HttpResponse response = httpclient.execute(httppost);
			
			int statusCode = response.getStatusLine().getStatusCode();
			HttpEntity responseEntity = response.getEntity();
			String responseString = EntityUtils.toString(responseEntity, "UTF-8");
			
			System.out.println("[" + statusCode + "] " + responseString);
			
		} catch (ClientProtocolException e) {
			System.err.println("Unable to make connection");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Unable to read file");
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) fis.close();
			} catch (IOException e) {}
		}
	    }
}

