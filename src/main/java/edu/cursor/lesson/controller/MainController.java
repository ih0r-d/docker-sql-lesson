package edu.cursor.lesson.controller;

import edu.cursor.lesson.model.User;
import edu.cursor.lesson.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    private final UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userRepository.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        userRepository.updateUserById(id, user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User " + user.toString() + " was updated successfully");
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userRepository.removeUserById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("User by id" + id + "was deleted successfully");
    }

}
