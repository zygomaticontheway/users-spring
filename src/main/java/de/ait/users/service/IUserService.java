package de.ait.users.service;

import de.ait.users.entity.User;

import java.util.List;

public interface IUserService {
//    List<User> findAll();
//    List<User> findByName(String name);

    List<User> getUsers(String name, String email);
    User createNewUser(User user);
    User findById(Long id);
    User updateUser (User user);
}
