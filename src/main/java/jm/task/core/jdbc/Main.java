package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.module.Configuration;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        //UserService workWithDb = new UserServiceImpl();
        //workWithDb.createUsersTable();
        //workWithDb.saveUser("Tom", "Cruise", (byte) 18);
        //workWithDb.saveUser("Bill", "Clinton", (byte) 6);
        //workWithDb.saveUser("Elon", "Musk", (byte) 34);
        //workWithDb.saveUser("Kylie", "Minogue", (byte) 90);
        //System.out.println(workWithDb.getAllUsers());
        //workWithDb.cleanUsersTable();
        //workWithDb.dropUsersTable();
    }
}
