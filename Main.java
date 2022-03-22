package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alex", "Alexov", (byte) 15);
        userService.saveUser("Tom", "Petrov", (byte) 20);
        userService.saveUser("Kate", "Ivanova", (byte) 25);
        userService.saveUser("Lusi", "Prizova", (byte) 30);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeConnection();
//        System.out.println(userService.getAllUsers());
    }
}
