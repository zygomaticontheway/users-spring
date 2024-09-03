package de.ait.users.service;

import de.ait.users.dto.UserRequestDto;
import de.ait.users.dto.UserResponseDto;
import de.ait.users.entity.User;
import de.ait.users.repository.IUserRepository;
import de.ait.users.repository.IUserRepositoryJpa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class UserServiceImp implements IUserService {

    private final IUserRepositoryJpa repository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImp(IUserRepositoryJpa repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UserResponseDto> findAll() {

        return UserResponseDto.of(repository.findAll());
    }

    @Override
    public List<UserResponseDto> getUsers(String name, String email) {
        Predicate<User> predicatedByName = (name.equals("") ? u -> true : u -> u.getName().equalsIgnoreCase(name));
        Predicate<User> predicatedByEmail = (email.equals("") ? u -> true : u -> u.getEmail().equalsIgnoreCase(email));

        //объединяет предикаты
        Predicate<User> allCondition = predicatedByEmail.and(predicatedByName);

        List<User> usersList = repository.findAll()
                .stream()
                .filter(allCondition)
                .toList();

        return UserResponseDto.of(usersList);
    }

    @Override
    public UserResponseDto createNewUser(UserRequestDto request) {

//        преобразование DTO в entity
//        User entity = repository.save(UserRequestDto.toEntity(request));
        User entity = repository.save(mapper.map(request, User.class));

//        return UserResponseDto.of(repository.save(entity));
        return  mapper.map(entity, UserResponseDto.class);
    }

    @Override
    public UserResponseDto findById(Long id) {

        return findAll()
                .stream()
                .filter(u -> u.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto request) {

        User entity = UserRequestDto.toEntity(request);
        entity.setId(id);

        return UserResponseDto.of(repository.save(entity));
    }

    public UserResponseDto findByName(String name) {

        return findAll()
                .stream()
                .filter(u -> u.getName().equals(name))
                .findAny()
                .orElse(null);
    }

}
