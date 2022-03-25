package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydbusers";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "dimitrovgrad";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();

                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, USERNAME);
                properties.put(Environment.PASS, PASSWORD);
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(properties).addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("yes connect!");
            } catch (Exception e) {
                System.out.println("no connect!");
            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() {

            try {
               Driver driver = new com.mysql.cj.jdbc.Driver();
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
