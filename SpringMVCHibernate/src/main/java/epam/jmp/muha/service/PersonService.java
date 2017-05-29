package epam.jmp.muha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epam.jmp.muha.dao.inter.IPersonDAO;
import epam.jmp.muha.entity.Person;
import epam.jmp.muha.service.inter.IPersonService;

@Service
public class PersonService implements IPersonService {
	
	@Autowired
	private IPersonDAO personDAO;

	public void setPersonDAO(IPersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	@Transactional
	public void addPerson(Person p) {
		this.personDAO.addPerson(p);
	}

	@Override
	@Transactional
	public void updatePerson(Person p) {
		this.personDAO.updatePerson(p);
	}

	@Override
	@Transactional
	public List<Person> listPersons() {
		return this.personDAO.listPersons();
	}
	
	@Override
	@Transactional
	public List<Person> listPersonsByPageNumber(long pageId, int totalPersonOnPage) {
		return this.personDAO.listPersonsByPageNumber(pageId, totalPersonOnPage);
	}

	@Override
	@Transactional
	public Person getPersonById(int id) {
		return this.personDAO.getPersonById(id);
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		this.personDAO.removePerson(id);
	}

	@Override
	@Transactional
	public Object calculatePageCount(int pageSize) 
	{
		int pageCount = (this.personDAO.getPersonCount()+(pageSize-1))/pageSize;
		return  pageCount;
	}

}
