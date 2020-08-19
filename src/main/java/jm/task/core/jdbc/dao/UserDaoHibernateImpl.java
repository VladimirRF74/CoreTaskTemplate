package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (final Session session = Util.getSf().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS testusers (id bigint auto_increment primary key NOT NULL , name varchar (255) NOT NULL, lastname varchar (255) NOT NULL, age smallint NOT NULL)").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (final Session session = Util.getSf().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("drop table IF EXISTS mytestdatabase.testusers").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSf().openSession()) {
            session.beginTransaction();
            User user = new User();
            user.setAge(age);
            user.setName(name);
            user.setLastName(lastName);
            session.save(user);
            session.getTransaction().commit();
        }

        System.out.printf("User с именем %s добавлен в базу данных\n", name);
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSf().openSession()) {
            session.beginTransaction();
            User user = session.find(User.class, id);
            if (user != null) {
                session.remove(user);
                session.getTransaction().commit();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSf().openSession()) {
            return session.createQuery("SELECT a FROM User a", User.class).getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSf().openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User w").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
