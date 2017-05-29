package epam.jmp.muha.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import epam.jmp.muha.dao.inter.IPersonDAO;
import epam.jmp.muha.entity.Person;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest 
{
	@InjectMocks
	public PersonService personService;
	@Mock
	private IPersonDAO personDAO;

	
	public void testSetPersonDAO(IPersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Test
	public void testAddPerson() 
	{
		Person p = new Person();
		personService.addPerson(p);
		verify(personDAO).addPerson(p);
	}

	@Test
	public void testUpdatePerson() 
	{
		Person p = new Person();
		personService.updatePerson(p);
		verify(personDAO).updatePerson(p);
	}
	
	@Test
	public void testRemovePerson()
	{
		int id = 3;
		personService.removePerson(id);
		verify(personDAO).removePerson(id);
	}

	@Test
	public void testGetPersonById()
	{
		int id=2;
		Person person = new Person();
		when(personDAO.getPersonById(id)).thenReturn(person);
		Person resultPerson = personService.getPersonById(id);
		verify(personDAO).getPersonById(any(Integer.class));
		assertEquals(person, resultPerson);
	}
	
	@Test
	public void testListPersons() 
	{
		Person person = new Person();
		Person person2 = new Person();
		List<Person> listPerson = new ArrayList<Person>();
		listPerson.add(person);
		listPerson.add(person2);
		when(personDAO.listPersons()).thenReturn(listPerson);
		List<Person> resultPersonList = personService.listPersons();
		verify(personDAO).listPersons();		
		assertEquals(2, resultPersonList.size());
	}
	
	@Test
	public void testListPersonsByPageNumber() 
	{
		Person person = new Person();
		Person person2 = new Person();
		List<Person> listPerson = new ArrayList<Person>();
		listPerson.add(person);
		listPerson.add(person2);
		long pageId = 2;
		int totalPersonOnPage = 2;
		when(personDAO.listPersonsByPageNumber(pageId, totalPersonOnPage)).thenReturn(listPerson);
		List<Person> resultPersonList = personService.listPersonsByPageNumber(pageId, totalPersonOnPage);
		verify(personDAO).listPersonsByPageNumber(pageId, totalPersonOnPage);		
		assertEquals(2, resultPersonList.size());
	}
 
	@Test
	public void testCalculatePageCount() 
	{
		int totalCount = 10;
		when(personDAO.getPersonCount()).thenReturn(totalCount);
		int result = (int) personService.calculatePageCount(3);
		assertEquals(4, result);
	}
}
