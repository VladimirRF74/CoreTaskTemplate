package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
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
