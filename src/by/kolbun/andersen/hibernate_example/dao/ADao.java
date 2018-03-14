package by.kolbun.andersen.hibernate_example.dao;

import by.kolbun.andersen.hibernate_example.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class ADao<T> implements IDao<T> {

    private final Session session = HibernateUtil.getSession();
    private Transaction transaction;


    /**
     * Delete record by id
     *
     * @param id - id of record to delete
     * @throws DaoException 1
     */
    @Override
    public void delete(long id) throws DaoException {
        try {
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

    /**
     * Insert new record or update old record if exists
     *
     * @param t - object to insert
     * @return inserted object
     * @throws DaoException 1
     */
    @Override
    public T saveOrUpdate(T t) throws DaoException {
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(t);
            session.update(t); //зачем дублировать?
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Dao Error saveOrUpdate(): ");
            e.printStackTrace();
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            if (session != null) session.close();
        }
        return t;
    }


    /**
     * find record and return object if exists
     * return @null if no object was found
     *
     * @param id - id of object to find
     * @return object <T> or null
     * @throws DaoException 1
     */
    @Override
    public T find(long id) throws DaoException {
        T t = null;
        try {
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

    public T load(long id) {
        T t;
        transaction = session.beginTransaction();
        t = (T) session.load(getPersistentClass(), id);
        transaction.commit();
//        session.close(); // если закрыть сессию, то из-за lazy-загрузки в месте обращения к полям объекта,
// который был возвращен методом, будет кидаться LazyInitializationException.
// В этом случае обращение в базу идет при обращении к полям,
// а сессия то уже закрыта...
        return t;
    }

    abstract Class getPersistentClass();
}