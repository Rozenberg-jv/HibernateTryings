package by.kolbun.andersen.hibernate_example.dao;

import by.kolbun.andersen.hibernate_example.entity.Book;

public class BookDao extends ADao<Book> {
    @Override
    protected Class getPersistentClass() {
        return Book.class;
    }

    /**
     * Overloaded method from @Class ADao
     *
     * @param title     - title of new book
     * @param ISBN - count of pages of new book
     * @return new object of saved Book or null
     */
    public Book saveOrUpdate(String title, int ISBN) throws DaoException {
        Book book = null;
        book = new Book();
        book.setTitle(title);
        book.setISBN(ISBN);
        saveOrUpdate(book);

        return book;
    }

    public void saveAndDelete(Book book) throws DaoException {
        saveOrUpdate(book);
        System.out.println(book);
        delete(book.getId());
    }
}
