package de.ait.users.service;

import de.ait.users.dto.UserRequestDto;
import de.ait.users.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers(String name, String email);
    User createNewUser(UserRequestDto request);
    User findById(Long id);
    User updateUser (Long id, UserRequestDto request);
}
