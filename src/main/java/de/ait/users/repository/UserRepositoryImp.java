package de.ait.users.repository;

import de.ait.users.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Component
@Repository //тоже работает для Spring, но всем понятнее что это репо
public class UserRepositoryImp implements IUserRepository {

    private List<User> database = new ArrayList<>( List.of(
            new User (1L,"Jack", "jack@email.cc", "123123"),
            new User (2L,"Ann", "annk@email.cc", "qwerdfsf"),
            new User (3L,"Jack", "jack1977@email.cc", "asdfsdf"),
            new User (4L,"Lena", "lena@email.cc", "41qweds")
    ));

    @Override
    public List<User> findAll() {
        return new ArrayList<>(database);
    }

    @Override
    public User save(User user) {
        if(user.getId() == null){
            //создание нового
            Long newId = database.get(database.size() -1).getId() + 1;
            user.setId(newId);
            database.add(user);
        } else{
            //изменение
            //TODO update user
        }
        return user;
    }
}
