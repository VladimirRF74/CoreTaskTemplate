package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = null;
    private Statement statement = null;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS testusers (id bigint auto_increment primary key NOT NULL , name varchar (30) NOT NULL, lastname varchar (30) NOT NULL, age smallint NOT NULL)";
        try {
            connection = Util.getMySQLConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try {
            connection = Util.getMySQLConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS mytestdatabase.testusers");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO mytestdatabase.testusers(name, lastname, age) VALUE (?, ?, ?)";
        try {
            connection = Util.getMySQLConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.execute();
            System.out.printf("User с именем %s добавлен в базу данных\n", name);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        PreparedStatement ps = null;
        try {
            connection = Util.getMySQLConnection();
            ps = connection.prepareStatement("DELETE FROM testusers WHERE id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        ResultSet rs = null;
        try {
            connection = Util.getMySQLConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM testusers");
            while (rs.next()) {
                //long id = rs.getLong(1);
                String name = rs.getString(2);
                String lastname = rs.getString(3);
                byte age = rs.getByte(4);
                User user = new User(name, lastname, age);
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usersList;
    }

    public void cleanUsersTable() {
        PreparedStatement ps = null;
        try {
            connection = Util.getMySQLConnection();
            ps = connection.prepareStatement("DELETE FROM testusers");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
