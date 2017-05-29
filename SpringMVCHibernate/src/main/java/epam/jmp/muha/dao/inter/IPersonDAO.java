package epam.jmp.muha.dao.inter;

import java.util.List;

import epam.jmp.muha.entity.Person;

public interface IPersonDAO {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public void removePerson(int id);
	public List<Person> listPersonsByPageNumber(long pageId, int totalPersonOnPage);
	public int getPersonCount();
}
