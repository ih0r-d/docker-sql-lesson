package edu.cursor.lesson.repository;

import edu.cursor.lesson.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final List<User> USERS = new ArrayList<>();


    @Override
    public void createUser(User user) {
        USERS.add(user);
    }

    @Override
    public void removeUserById(Long userId) {
        USERS.removeIf(u -> Objects.equals(u.getId(), userId));
    }

    @Override
    public List<User> findAll() {
        return USERS;
    }

    @Override
    public void updateUserById(Long id, User user) {
        USERS.removeIf(u -> Objects.equals(u.getId(), id));
        USERS.add(user);
    }

    @Override
    public User findUserById(Long id) {
        return USERS.get(Math.toIntExact(id));
    }
}
