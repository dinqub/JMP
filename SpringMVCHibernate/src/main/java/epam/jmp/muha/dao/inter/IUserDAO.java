package epam.jmp.muha.dao.inter;

import epam.jmp.muha.entity.User;

public interface IUserDAO {

	public User findByUsername(String username);

	public void save(User user);
}
