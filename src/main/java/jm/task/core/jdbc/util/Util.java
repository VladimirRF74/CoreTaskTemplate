package jm.task.core.jdbc.util;

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
}
