package com.kadzys.VBooks.repositories;

import com.kadzys.VBooks.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByName(String username);
    boolean existsUserByName(String name);
}
