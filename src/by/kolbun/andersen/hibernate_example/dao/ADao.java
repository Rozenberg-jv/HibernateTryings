package by.kolbun.andersen.hibernate_example.dao;

import by.kolbun.andersen.hibernate_example.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class ADao<T> implements IDao<T> {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Transaction transaction;
    private Class T;

    @Override
    public void delete(long id) throws DaoException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            T t = (T) session.get(getPersistentClass(), id);
            session.delete(t);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Dao Error delete(): " + e);
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public T saveOrUpdate(T t) throws DaoException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(t);
            session.update(t);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Dao Error saveOrUpdate(): " + e);
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            if (session != null) session.close();
        }
        return t;
    }

    @Override
    public T find(long id) throws DaoException {
        T t = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            t = (T) session.get(getPersistentClass(), id);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Dao Error find(): " + e);
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            if (session != null) session.close();
        }
        return t;
    }

    private Class getPersistentClass() {
        return T;
    }
}
