package de.ait.users.repository;

import de.ait.users.entity.User;

import java.util.List;

public interface IUserRepository {
    List<User>findAll();
    User save(User user);

}
