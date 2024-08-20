package de.ait.users.repository;

import de.ait.users.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            return createUser(user);

        } else{
           return updateUser(user);
        }
    }

    private User createUser(User user) {
        Long newId = database.get(database.size() -1).getId() + 1;
        user.setId(newId);
        database.add(user);
        return user;
    }

    private User updateUser (User user) {
        Optional<User> userFromDb = findById(user.getId());

        if(userFromDb.isEmpty()){
            return null;
        } else {
            User u = userFromDb.get();
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
        }
        return user;
    }

    private Optional<User> findById (Long id) {
      return database.stream()
              .filter(u -> u.getId().equals(id))
              .findFirst();
    };

}
