package epam.jmp.muha.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import epam.jmp.muha.dao.inter.IUserDAO;
import epam.jmp.muha.entity.User;

@Repository("UserDao")
public class UserDAO implements IUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public User findByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();		
		
		Criteria criteria = session.createCriteria(User.class);
		User user = (User) criteria.add(Restrictions.eq("username", username))
		                             .uniqueResult();
		return user;
	}

	@Override
	public void save(User user)
	{
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);		
	}

}
