package gr.hua.ds.club_registry.service.impl;

import java.util.List;

import gr.hua.ds.club_registry.db.enums.Roles;
import gr.hua.ds.club_registry.db.models.User;
import gr.hua.ds.club_registry.db.repository.UserRepository;
import gr.hua.ds.club_registry.rest.exception.UserNotFoundException;
import gr.hua.ds.club_registry.service.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userDAO;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public User findUser( String username ) {
        return  userDAO.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public List <User> findAllUsers() {
        return (List<User>) userDAO.findAll();
    }

    @Override
    public List <User> findAllGGASecretaries() {
        return  userDAO.findByUserRole(Roles.ROLE_GGA);
    }

    @Override
    public List <User> findAllHellenicPoliceSecretaries() {
        return  userDAO.findByUserRole(Roles.ROLE_POLICE);
    }

    @Override
    public List <User> findAllClubSupervisors() {
        return userDAO.findByUserRole(Roles.ROLE_CLUB_SUPERVISOR);
    }

    @Override
    public User insertUser(User user ) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    @Override
    public User updateUser(User oldUser , User newUser ) {
        oldUser.setUsername(newUser.getUsername());
        oldUser.setEmail(newUser.getEmail());
        return userDAO.save(oldUser);
    }

    @Override
    public void deleteUser( User user ) {
        userDAO.delete(user);
    }
}
