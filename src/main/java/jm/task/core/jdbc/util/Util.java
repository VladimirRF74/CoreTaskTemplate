package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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

    private static ServiceRegistry registry;
    private static SessionFactory sf = null;

    public static SessionFactory getSf() {
        if (sf == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty( "hibernate.connection.driver_class",
                                "com.mysql.jdbc.Driver" )
                        .setProperty( "hibernate.connection.url",
                                "jdbc:mysql://localhost:3306/mytestdatabase" )
                        .setProperty( "hibernate.connection.username",
                                "root" )
                        .setProperty( "hibernate.connection.password",
                                "root" )
                        .setProperty( "hibernate.connection.pool_size", "1" )
                        .setProperty( "hibernate.connection.autocommit", "false" )
                        .setProperty( "hibernate.cache.provider_class",
                                "org.hibernate.cache.NoCacheProvider" )
                        .setProperty( "hibernate.cache.use_second_level_cache",
                                "false" )
                        .setProperty( "hibernate.cache.use_query_cache", "false" )
                        .setProperty( "hibernate.dialect",
                                "org.hibernate.dialect.MySQL8Dialect" )
                        .setProperty( "hibernate.show_sql","true" )
                        .setProperty( "hibernate.current_session_context_class",
                                "thread" )
                        .addPackage( "jm.task.core.jdbc" )
                        .addAnnotatedClass(User.class)
                        ;
                registry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
                return configuration.buildSessionFactory(registry);

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sf;
    }

    public static void close() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
