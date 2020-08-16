package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getMySQLConnection() throws SQLException {
        String userName = "root";
        String password = "root";
        String connectionURL = "jdbc:mysql://localhost:3306/mytestdatabase?useSSL=false&serverTimezone=Europe/Moscow&allowPublicKeyRetrieval=true";
        return DriverManager.getConnection(connectionURL, userName, password);
    }
    private static final SessionFactory sf = createSession();

    private static SessionFactory createSession() {
        return new Configuration().configure().buildSessionFactory();
    }
    public static SessionFactory getSf() {
        return sf;
    }
    public static void closeSession() {
        sf.close();
    }
}
