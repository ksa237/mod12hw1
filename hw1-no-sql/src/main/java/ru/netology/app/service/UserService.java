package ru.netology.app.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.app.model.User;
import ru.netology.app.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor // нужна для создания конструктора с заполнением поля userRepository
public class UserService {

    private final UserRepository userRepository;
    private final int PERCENT_APPROXIMATE = 10;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }


    public User updateUser(String id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public User findUserByEmail(String email) {

        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("email указан некорректно");
        }


        return userRepository.findByEmail(email);
    }

    public List<User> findUsersByName(String name) {
        return userRepository.findByNameContaining(name);
    }

    public List<User> findUsersByAgeAccurate(Integer age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Возраст точного поиска должен быть больше 0");
        }

        return userRepository.findByAge(age);
    }

    public List<User> findUsersByAgeApproximate(Integer age) {

        if (age < 5) {
            throw new IllegalArgumentException("Возраст для сравнения должен быть больше 4");
        }

        double delta = age * PERCENT_APPROXIMATE / 100.0;
        int roundedDelta = (int) Math.round(delta);

        int ageMin = age - roundedDelta;
        int ageMax = age + roundedDelta;

        return userRepository.findByAgeBetween(ageMin, ageMax);

    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }


}
