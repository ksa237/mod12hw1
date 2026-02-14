package ru.netology.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.app.model.User;
import ru.netology.app.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor // нужна для создания конструктора с заполнением поля userService
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable String id,
            @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/emails")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @GetMapping("/names")
    public ResponseEntity<List<User>> getUsersByName(@RequestParam String name){
        return ResponseEntity.ok(userService.findUsersByName(name));
    }

   @GetMapping("/ageaccurate/{age}")
    public ResponseEntity<List<User>> getByAgeAccurate(@PathVariable Integer age){
        return ResponseEntity.ok(userService.findUsersByAgeAccurate(age));
    }


    @GetMapping("/ageapproximate/{age}")
    public ResponseEntity<List<User>> getByAgeApproximate(@PathVariable Integer age){
        return ResponseEntity.ok(userService.findUsersByAgeApproximate(age));
    }


}
