package de.ait.users.service;

import de.ait.users.dto.UserRequestDto;
import de.ait.users.dto.UserResponseDto;

import java.util.List;

public interface IUserService {

    List<UserResponseDto> getUsers(String name, String email);
    UserResponseDto createNewUser(UserRequestDto request);
    UserResponseDto findById(Long id);
    UserResponseDto updateUser (Long id, UserRequestDto request);
}
