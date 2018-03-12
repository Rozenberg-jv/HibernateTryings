package by.kolbun.andersen.hibernate_example.dao;

import by.kolbun.andersen.hibernate_example.entity.Book;

public class BookDao extends ADao<Book> {
    @Override
    protected Class getPersistentClass() {
        return Book.class;
    }
}
