package gr.hua.ds.club_registry.service.service;

import java.util.List;

import gr.hua.ds.club_registry.db.models.User;

public interface UserService {
    public User findUser(String username);
    public List <User> findAllUsers();
    public List<User> findAllGGASecretaries();
    public List<User> findAllHellenicPoliceSecretaries();
    public List<User>  findAllClubSupervisors();
    public User insertUser(User user);
    public User updateUser(User oldUser, User newUser);
    public void deleteUser(User user);

}


