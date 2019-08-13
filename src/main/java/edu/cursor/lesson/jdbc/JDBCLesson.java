package edu.cursor.lesson.jdbc;


import edu.cursor.lesson.model.User;

import java.sql.*;

public class JDBCLesson {

    private static final String URL = "jdbc:postgresql://localhost:5432/lesson_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String DRIVER = "org.postgresql.Driver";

    public static void main(String[] args) {
        try (Connection connection=DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            Class.forName(DRIVER);
            createUser(connection, new User(11L,"testName","test@mail.com","teSTPAswd"));
            findAll(connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void findAll(Connection connection) throws SQLException {
        ResultSet resultSet = connection.prepareStatement("SELECT * FROM users")
                                        .executeQuery();

        while (resultSet.next()) {
            System.out.println("{\nid - " + resultSet.getString(1)+ ", \n" +
                                " username - " + resultSet.getString(2)+ ", \n" +
                                " email - " + resultSet.getString(3)+ ", \n" +
                                " password - " + resultSet.getString(4) + "\n}\n");
        }
    }
    private static void createUser(Connection connection,User user) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("INSERT INTO users(id, username, email, password) VALUES (?,?,?,?)");

        statement.setLong(1,user.getId());
        statement.setString(2,user.getUsername());
        statement.setString(3,user.getEmail());
        statement.setString(4, user.getPassword());

        statement.executeUpdate();
    }

}
