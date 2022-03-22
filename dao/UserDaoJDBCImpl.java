package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

   private final Connection connection;

    public UserDaoJDBCImpl() {
       connection = Util.getConnection();
    }

    public void createUsersTable() {
           try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS mydbusers.users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(dropTable)) {
            preparedStatement.executeUpdate();
            System.out.println("таблица удалена");
        } catch (SQLException e) {
            System.out.println("таблица не удалена");;
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(saveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User " + name + " " + lastName + " добавлен в базу данных.");
        } catch (SQLException e) {
            System.out.println("Не удалось внести указанного пользователя.");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeById = "DELETE FROM users WHERE id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeById)) {
            preparedStatement.executeUpdate();
            System.out.println("пользователь под " + id + " номером удален.");
        } catch (SQLException e) {
            System.out.println("пользователь под " + id + " номером НЕ удален.");;
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String getAll = "SELECT * FROM users";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAll);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            System.out.println("лист создан");
        } catch (SQLException e) {
            System.out.println("ошибка при создании листа");
        }
        return userList;
    }

    public void cleanUsersTable() {
        String cleanTable = "TRUNCATE TABLE users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(cleanTable)) {
            preparedStatement.executeUpdate();
            System.out.println("таблица чиста!");
        } catch (SQLException e) {
            System.out.println("таблица не отчищена");;
        }
    }
}
