package edu.cursor.lesson.repository;

import edu.cursor.lesson.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Slf4j
@PropertySource("classpath:db.properties")
public class UserRepositoryJdbcImpl implements UserRepository {

    @Value("${User.createUser}")
    private String qCreateUser;

    @Value("${User.findAll}")
    private String qfindAll;

    @Value("${User.findById}")
    private String qfindById;

    @Value("${User.updateUser}")
    private String qUpdateUser;

    @Value("${User.deleteUser}")
    private String qDeleteUser;

    //    private final DataSource dataSource;
//    private JdbcTemplate jdbcTemplate;
//
//    public UserRepositoryJdbcImpl(DataSource dataSource) {
//        this.dataSource = dataSource;
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }
    JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void createUser(User user) {
        jdbcTemplate.update(qCreateUser, user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
        log.info("User successfully created . . ");
    }

    @Override
    public void removeUserById(Long userId) {
        jdbcTemplate.update(qDeleteUser, userId);
        log.info("User with id: {} successfully removed", userId);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(qfindAll, new UserMapper());
    }

    @Override
    public void updateUserById(Long id, User user) {
        jdbcTemplate.update(qUpdateUser, user.getUsername(), user.getEmail(), user.getPassword(), user.getId());
        log.info("User with id: {} successfully updated", id);
    }

    @Override
    public User findUserById(Long id) {
        return jdbcTemplate.queryForObject(qfindById, new Object[]{id}, new UserMapper());
    }
}
