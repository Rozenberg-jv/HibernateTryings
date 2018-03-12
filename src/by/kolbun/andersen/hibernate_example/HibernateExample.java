package by.kolbun.andersen.hibernate_example;

import by.kolbun.andersen.hibernate_example.entity.Book;
import org.hibernate.Session;

public class HibernateExample {

    //// TODO: 12.03.2018 разобраться с log4j: какие библиотеки нужны в минимальном варианте

    public void exec() {
//        Logger logger = LogManager.getLogger("CommonLog");

        Session session = HibernateUtil.getSessionFactory().openSession();



        session.flush();
        session.close();

    }
}
