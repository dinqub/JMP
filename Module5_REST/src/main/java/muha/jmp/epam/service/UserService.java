package muha.jmp.epam.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import muha.jmp.epam.model.User;

@Path("/user")
public class UserService 
{
	public static Map<Integer, User> database = new HashMap<Integer, User>();

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public User createUser(User user) 
	{
		Integer dabaseSize = database.size();
		user.setId(dabaseSize+1);
		database.put(user.getId(), user);
		return user;		
	}
	@GET
	@Path("/read/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public User readUser(@PathParam("param") Integer id) 
	{
		return database.get(id);
	}
	
	@GET
	@Path("/downloadLogo/{param}")
	@Produces("image/png")
	public Response downloadLogo(@PathParam("param") Integer id) 
	{
		File file = new File(database.get(id).getImageAdress());
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=image_from_server.png");
		return response.build();

	}
	
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User getTrackInJSON(User user) 
	{
		User updatedUser = database.get(user.getId());
		if (user.getFirstName() != null)
		{
			updatedUser.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null)
		{
			updatedUser.setLastName(user.getLastName());
		}
		if (user.getLogin() != null)
		{
			updatedUser.setLogin(user.getLogin());
		}
		if (user.getEmail() != null)
		{
			updatedUser.setEmail(user.getEmail());
		}
		return updatedUser;

	}
	@POST
	@Path("/updateLogo/{param}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadLogo(@PathParam("param") String id,
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String uploadedFileLocation = "e://Downloads//" + fileDetail.getFileName();
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);
		
		database.get(Integer.parseInt(id)).setImageAdress(uploadedFileLocation);
		String output = "Logo uploaded";
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/delete/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeUser(@PathParam("param") Integer id) 
	{
		database.remove(id);		
		return Response.status(200).entity("").build();
	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
		String uploadedFileLocation) 
	{
		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
}