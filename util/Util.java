package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Util {
    // реализуйте настройку соеденения с БД
    static Driver driver;
    private static final String URL = "jdbc:mysql://localhost:3306/mydbusers";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "dimitrovgrad";

    public static Connection getConnection() {

            try {
                driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);

            } catch (SQLException e) {
                System.out.println("ошибка при подключении к БД");
            }
            return null;
    }
    public static void closeConnection() {
        try {
            Objects.requireNonNull(getConnection()).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
