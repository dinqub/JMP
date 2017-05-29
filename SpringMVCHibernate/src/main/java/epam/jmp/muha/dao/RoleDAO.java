package epam.jmp.muha.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import epam.jmp.muha.dao.inter.IRoleDAO;
import epam.jmp.muha.entity.Role;

@Repository
public class RoleDAO implements IRoleDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public Role getRoleById(long id)
	{
		Session session = this.sessionFactory.getCurrentSession();		
		Role role = (Role) session.load(Role.class, new Long(id));
		return role;
	}

}
