package by.kolbun.andersen.hibernate_example;

import by.kolbun.andersen.hibernate_example.dao.BookDao;
import by.kolbun.andersen.hibernate_example.dao.DaoException;
import by.kolbun.andersen.hibernate_example.entity.Book;
import org.hibernate.Session;

public class HibernateExample {

    //// TODO: 12.03.2018 разобраться с log4j: какие библиотеки нужны в минимальном варианте

    public void exec() {
//        Logger logger = LogManager.getLogger("CommonLog");

        Session session = HibernateUtil.getSessionFactory().openSession();

        BookDao bookDao = new BookDao();
        Book book = new Book();
        book.setTitle("Drei kleine Schweine");
        book.setPageCount(35);

        try {
            bookDao.saveOrUpdate(book);
            System.out.println("saved");
            book.setPageCount(40);
            System.out.println(bookDao.saveOrUpdate(book));
            System.out.println("updated");
            System.out.println(bookDao.find(book.getId()));
            System.out.println("found by id");
        } catch (DaoException e) {
            e.printStackTrace();
        }

        session.flush();
        session.close();

    }
}
