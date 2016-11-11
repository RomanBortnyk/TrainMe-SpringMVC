package trainMe.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

/**
 * Created by romab on 11/10/16.
 */
@Component
public class HibernateUtil {

    private static SessionFactory sessionFactory;


    public void setSessionFactory (SessionFactory sessionFactory){
        HibernateUtil.sessionFactory = sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Convenience method to get the current session.
     */
    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Empty method. A call to this method causes the static initializer to be run, and thus boots Hibernate.
     */
    public static void boot() {}

    /**
     * Shuts down Hibernate, otherwise the Main thread may not be closed.
     */
    public static void shutdown() {
        sessionFactory.close();
    }

}
