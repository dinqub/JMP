package epam.jmp.muha.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import epam.jmp.muha.entity.Person;
import epam.jmp.muha.service.inter.IPersonService;

@RestController
public class PersonRestController
{
	@Autowired
	IPersonService personService;

	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(IPersonService ps){
		this.personService = ps;
	}
	@Autowired
	ServletContext context;
	// -------------------Retrieve All
		// Persons--------------------------------------------------------
		@RequestMapping(value = "/person", method = RequestMethod.GET)
		public ResponseEntity<List<Person>> listAllPersons() {
			List<Person> persons = personService.listPersons();
			if (persons.isEmpty()) {
				return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
		}

		// -------------------Retrieve Single
		// Product--------------------------------------------------------

		@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
		public ResponseEntity<Person> getPerson(@PathVariable("id") int id) {
			System.out.println("Fetching Person with id " + id);
			Person person = personService.getPersonById(id);
			if (person == null) {
				System.out.println("Person with id " + id + " not found");
				return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		}

		// -------------------Create a
		// User--------------------------------------------------------

		@RequestMapping(value = "/person", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Void> createPerson(@RequestBody Person person, UriComponentsBuilder ucBuilder) 
		{
			System.out.println("Creating Person " + person.getName());
			Integer id = person.getId();
			
			personService.addPerson(person);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/person/{id}").buildAndExpand(person.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

		// ------------------- Update a Product
		// --------------------------------------------------------

		@RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Person> updatePerson(@PathVariable("id") int id, @RequestBody Person person) {
			System.out.println("Updating Person " + id);

			Person currentPerson = personService.getPersonById(id);

			if (currentPerson == null) 
			{
				System.out.println("Person with id " + id + " not found");
				return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
			}

			currentPerson.setName(person.getName());
			currentPerson.setSurname(person.getSurname());

			personService.updatePerson(currentPerson);
			return new ResponseEntity<Person>(currentPerson, HttpStatus.OK);
		}

		// ------------------- Delete a Person
		// --------------------------------------------------------

		@RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Person> deletePerson(@PathVariable("id") int id) {
			System.out.println("Fetching & Deleting Person with id " + id);

			Person person = personService.getPersonById(id);
			if (person == null) {
				System.out.println("Unable to delete. Product with id " + id + " not found");
				return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
			}

			personService.removePerson(id);
			return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
		}

		// ---------------------------File uploading
		// --------------------------------
/*		@RequestMapping(value = "/fileupload/{i}", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
		public ResponseEntity<FileInfo> upload(@RequestParam("file") MultipartFile inputFile,@PathVariable("id") int id) {
			FileInfo fileInfo = new FileInfo();
			HttpHeaders headers = new HttpHeaders();
			if (!inputFile.isEmpty()) {
				try {
					String originalFilename = inputFile.getOriginalFilename();
					File destinationFile = new File(
							context.getRealPath("/WEB-INF/uploaded") + File.separator + originalFilename);
					inputFile.transferTo(destinationFile);
					fileInfo.setFileName(destinationFile.getPath());
					fileInfo.setFileSize(inputFile.getSize());
					headers.add("File Uploaded Successfully - ", originalFilename);
					Person currentPerson = personService.getPersonById(id);
					currentPerson.setPhoto(originalFilename);
					personService.updatePerson(currentPerson);
					return new ResponseEntity<FileInfo>(fileInfo, headers, HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
			}
		}*/
}
