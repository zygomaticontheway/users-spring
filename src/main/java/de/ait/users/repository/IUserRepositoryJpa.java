package de.ait.users.repository;

import de.ait.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepositoryJpa extends JpaRepository<User, Long> {

}
