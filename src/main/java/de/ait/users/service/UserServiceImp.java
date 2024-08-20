package de.ait.users.service;

import de.ait.users.dto.UserRequestDto;
import de.ait.users.entity.User;
import de.ait.users.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class UserServiceImp implements IUserService {

    private final IUserRepository repository;

    @Autowired
    public UserServiceImp(@Qualifier("getRepository") IUserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public List<User> getUsers(String name, String email) {
        Predicate<User> predicatedByName = (name.equals("") ? u -> true : u -> u.getName().equalsIgnoreCase(name));
        Predicate<User> predicatedByEmail = (email.equals("") ? u -> true : u -> u.getEmail().equalsIgnoreCase(email));

        //объединяет предикаты
        Predicate<User> allCondition = predicatedByEmail.and(predicatedByName);

        return repository.findAll()
                .stream()
                .filter(allCondition)
                .toList();
    }

    @Override
    public User createNewUser(UserRequestDto request) {

//        преобразование DTO в entity
        User entity = UserRequestDto.toEntity(request);

        return repository.save(entity);
    }

    @Override
    public User findById(Long id) {
        return findAll()
                .stream()
                .filter(u -> u.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public User updateUser(Long id, UserRequestDto request) {

        User entity = UserRequestDto.toEntity(request);
        entity.setId(id);

        return repository.save(entity);
    }

}
