package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    //UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userHiber = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userHiber.createUsersTable();
    }

    public void dropUsersTable() {
        userHiber.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {

        userHiber.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userHiber.removeUserById(id);
    }

    public List<User> getAllUsers() {

        return userHiber.getAllUsers();
    }

    public void cleanUsersTable() {
        userHiber.cleanUsersTable();

    }
}
