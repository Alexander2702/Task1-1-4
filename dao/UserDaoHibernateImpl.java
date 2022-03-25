package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, " +
                "lastName VARCHAR(255) NOT NULL, age INT NOT NULL)";
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery(createTable).executeUpdate();
            System.out.println("таблица создана");
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("таблица НЕ создана");
        }
    }

    @Override
    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS users";
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery(dropTable).executeUpdate();
            System.out.println("таблица удалена");
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("таблица НЕ удалена");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.save(user);
            System.out.println("User " + name + " " + lastName + " добавлен в базу данных.");
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("user НЕ создан");
        }
    }

    @Override
    public void removeUserById(long id) {
        String removeUser = "DELETE FROM users WHERE id";
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery(removeUser).executeUpdate();
            System.out.println("пользователь под " + id + " номером удален.");
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("пользователь под \" + id + \" номером НЕ удален.");
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from User").list();
    }

    @Override
    public void cleanUsersTable() {
        String cleanTable = "TRUNCATE TABLE users";
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery(cleanTable).executeUpdate();
            System.out.println("таблица чиста");
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("таблица НЕ очищена");
        }
    }
}
