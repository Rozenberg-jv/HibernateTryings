package by.kolbun.andersen.hibernate_example;

import by.kolbun.andersen.hibernate_example.entity.Book;
import by.kolbun.andersen.hibernate_example.entity.BookInfo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(BookInfo.class).addAnnotatedClass(Book.class);
            configuration.configure("by/kolbun/andersen/hibernate_example/resources/hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    private static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
