package epam.jmp.muha.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import epam.jmp.muha.entity.Person;
import epam.jmp.muha.service.inter.IPersonService;
import epam.jmp.muha.validator.PersonValidator;

@Controller
public class PersonController extends ErrorController
{
/*	@Value("${file.root_directory}")
	public String ROOT_DIRECTORY ;
	@Value("${file.upload_directory}")
	public String UPLOAD_DIRECTORY ;*/
	private static final int BUFFER_SIZE = 4096;
	private static final int PAGE_SIZE = 3;
	
	@Autowired
	private IPersonService personService;
		
    @Autowired
    private PersonValidator personValidator;
	
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(IPersonService ps){
		this.personService = ps;
	}
		
	@RequestMapping(value = "/listpersons", method = RequestMethod.GET)
	public String listPersons(Model model,@RequestParam(value = "pageId", required = false) Long pageId)
	{
		if (pageId == null)
		{
			pageId = new Long(1);
		}
		model.addAttribute("pageId", pageId);
		model.addAttribute("pageCount", this.personService.calculatePageCount(PAGE_SIZE));
		model.addAttribute("listPersons", this.personService.listPersonsByPageNumber(pageId, PAGE_SIZE));
		return "person";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String emptyPath(Model model, RedirectAttributes redirectAttributes)
	{
		Long pageId = new Long(1);		
		redirectAttributes.addAttribute("pageId", pageId);
		return "redirect:listpersons";
	}
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String paginationListPersons(Model model, RedirectAttributes redirectAttributes)
	{
		Long pageId = new Long(1);		
		redirectAttributes.addAttribute("pageId", pageId);
		return "redirect:listpersons";
	}
	
	@RequestMapping(value = "/addperson", method = RequestMethod.GET)
	public String addPersonPage(Model model, RedirectAttributes redirectAttributes)
	{
		model.addAttribute("person", new Person());
		return "addperson";
	}
	
	//For add and update person both
	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person")  Person p, BindingResult bindingResult, Model model) 
	{
        personValidator.validate(p, bindingResult);
        if (bindingResult.hasErrors())
        {
            return "addperson";
        }        
		this.personService.addPerson(p);

		return "redirect:/persons";
		
	}
	//For add and update person both
	@RequestMapping(value= "/person/edit", method = RequestMethod.POST)
	public String editPerson(@ModelAttribute("person")  Person p, BindingResult bindingResult, Model model) 
	{
        personValidator.validate(p, bindingResult);
        if (bindingResult.hasErrors())
        {
            return "editperson";
        } 
		this.personService.updatePerson(p);
		
		return "redirect:/persons";		
	}
	
	//For add and update person both
	@RequestMapping(value= "/upload/{id}", method = RequestMethod.GET)
	public String goToUploadPage(@PathVariable("id") int id, Model model) 
	{
		model.addAttribute("personId", id);
		return "upload";
	}
	
	//For add and update person both
	@RequestMapping(value= "/upload/upload", method = RequestMethod.POST)
	public String uploadImage(@RequestParam("id") int id,
			@RequestParam CommonsMultipartFile file, HttpSession session) throws Exception
	{
		String name =  File.separator+Integer.toString(id)+".png";
		String uploadResult = uploadImage(name, file);
		if(uploadResult != null)
		{
			 return uploadResult;
		}		
		
		Person p = personService.getPersonById(id);
		p.setPhoto(name);
		this.personService.updatePerson(p);		
		
		return "redirect:/persons";
	}
	
	@RequestMapping(value= "/download/{id}", method = RequestMethod.GET)
    public void downloadPhoto(@PathVariable("id") int id, HttpServletResponse response) 
    		throws IOException {

        // construct the complete absolute path of the file
		String fullPath =System.getProperty("catalina.base")+File.separator+Integer.toString(id)+".png";
		File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        // get MIME type of the file
        String mimeType = "application/octet-stream";
    
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
 
    }
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.personService.removePerson(id);
        return "redirect:/persons";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        return "editperson";
    }
	
	private String uploadImage(String name, CommonsMultipartFile file)
    {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				
				// Creating the directory to store file
				File dir = new File(System.getProperty("catalina.base"));
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name
					+ " because the file was empty.";
		}
		return null;		
    }


	public PersonValidator getPersonValidator() {
		return personValidator;
	}

	public void setPersonValidator(PersonValidator personValidator) {
		this.personValidator = personValidator;
	}

	public IPersonService getPersonService() {
		return personService;
	}
	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
