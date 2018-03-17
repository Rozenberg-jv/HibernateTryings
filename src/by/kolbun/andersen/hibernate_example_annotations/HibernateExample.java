package by.kolbun.andersen.hibernate_example_annotations;

import by.kolbun.andersen.hibernate_example_annotations.dao.BookDao;
import by.kolbun.andersen.hibernate_example_annotations.entity.Author;
import by.kolbun.andersen.hibernate_example_annotations.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class HibernateExample {

    public void exec() {

        BookDao dao = new BookDao();

        List l = new ArrayList();
        l.add(new Author("a", 3, "a"));
        l.add(new Author("a", 3, "a"));
        Book b = new Book("name3", 1238, l);
        System.out.println("before add: " + b);
        int id = dao.add(b);
        System.out.println("after add: " + b);

        List l2 = new ArrayList();
        l2.add(new Author("b", 2, "c"));
        b.setAuthors(l2);
        dao.update(b);
        System.out.println("after update: " + b);

//        dao.delete(id);

        System.out.println("after delete: " + b);

        dao.getAll().forEach(System.out::println);

    }
}
