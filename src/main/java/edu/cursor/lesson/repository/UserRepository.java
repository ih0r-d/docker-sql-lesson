package edu.cursor.lesson.repository;

import edu.cursor.lesson.model.User;

import java.util.List;

public interface UserRepository {
    void createUser(User user);

    void removeUserById(Long userId);

    List<User> findAll();

    void updateUserById(Long id, User user);

}
