package epam.jmp.muha.service.inter;

import epam.jmp.muha.entity.User;


public interface IUserService {

    void save(User user);

    User findByUsername(String username);
}
