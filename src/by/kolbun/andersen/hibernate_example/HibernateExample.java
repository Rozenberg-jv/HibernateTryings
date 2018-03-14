package by.kolbun.andersen.hibernate_example;

import by.kolbun.andersen.hibernate_example.dao.BookDao;
import by.kolbun.andersen.hibernate_example.dao.DaoException;
import by.kolbun.andersen.hibernate_example.entity.Book;
import by.kolbun.andersen.hibernate_example.entity.BookInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateExample {

    //// TODO: 12.03.2018 разобраться с log4j: какие библиотеки нужны в минимальном варианте

    public void exec() {
//        Logger logger = LogManager.getLogger("CommonLog");

        BookDao dao = new BookDao();
        try {
            dao.saveOrUpdate("qwe", 34123);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        /*BookDao dao = new BookDao();
        Book book = new Book();
        book.setISBN(123458);
        book.setTitle("test Title 1");
        BookInfo info = new BookInfo();
        info.setPageCount(50);
        info.setFormat("B3");
        info.setId(14L);

        book.setInfo(info);
        info.setBook(book);

        try {
            dao.saveOrUpdate(book);
        } catch (DaoException e) {
            e.printStackTrace();
        }*/


        /*Session session = HibernateUtil.getSession();
        Transaction trans = session.getTransaction();
        trans.begin();
        Book book = new Book();
        book.setTitle("Drei kleine Schweine");
        book.setISBN(531412);
        System.out.println("pure book: " + book);
        BookInfo info = new BookInfo();
        info.setPageCount(35);
        info.setFormat("A4");
        System.out.println("pure info: " + info);
        book.setInfo(info);
        info.setBook(book);
        System.out.println("book after: " + book);
        System.out.println("info after: " + info);
        session.save(book);
        trans.commit();
        session.close();*/


    }
}
