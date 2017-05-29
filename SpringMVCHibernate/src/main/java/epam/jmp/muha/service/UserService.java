package epam.jmp.muha.service;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epam.jmp.muha.dao.inter.IRoleDAO;
import epam.jmp.muha.dao.inter.IUserDAO;
import epam.jmp.muha.entity.Role;
import epam.jmp.muha.entity.User;
import epam.jmp.muha.service.inter.IUserService;


@Service("userService")
public class UserService implements IUserService {

    @Autowired
    @Qualifier("UserDao")
    private IUserDAO userDao;

    @Autowired
    private IRoleDAO roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getRoleById(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
