package by.kolbun.andersen.hibernate_example.dao;

import org.hibernate.HibernateException;

public class DaoException extends Exception {
    public DaoException(HibernateException e) {
        System.out.println(e);
    }
}
