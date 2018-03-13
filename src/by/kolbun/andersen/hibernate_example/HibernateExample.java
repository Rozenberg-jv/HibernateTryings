package by.kolbun.andersen.hibernate_example;

import by.kolbun.andersen.hibernate_example.dao.BookDao;
import by.kolbun.andersen.hibernate_example.dao.DaoException;
import by.kolbun.andersen.hibernate_example.entity.Book;

public class HibernateExample {

    //// TODO: 12.03.2018 разобраться с log4j: какие библиотеки нужны в минимальном варианте

    public void exec() {
//        Logger logger = LogManager.getLogger("CommonLog");

        BookDao bookDao = new BookDao();


    }
}
