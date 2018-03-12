package by.kolbun.andersen.hibernate_example.dao;

public interface IDao<T> {
    void delete(long id) throws DaoException;
    T saveOrUpdate(T t) throws DaoException;
    T find(long id) throws DaoException;
}
