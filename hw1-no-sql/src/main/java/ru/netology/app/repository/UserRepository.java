package ru.netology.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.netology.app.model.User;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    List<User> findByNameContaining(String name);

    List<User> findByAge(Integer age);

    List<User> findByAgeBetween(Integer minAge, Integer maxAge);

}
