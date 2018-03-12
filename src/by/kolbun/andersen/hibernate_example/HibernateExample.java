package by.kolbun.andersen.hibernate_example;

import org.hibernate.Session;

public class HibernateExample {

    public void exec() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Book book = new Book();

        book.setPageCount(520);
        book.setTitle("Tales of Round Table");
        System.out.println("0: " + book);
        session.save(book);
        Book book2 = (Book) session.get(Book.class, book.getId());
        System.out.println("1: " + book2);
        book2.setPageCount(430);
        System.out.println("2: " + book2);
        session.save(book2);
        book = (Book) session.get(Book.class, book.getId());
        System.out.println("3: " + book);
        session.delete(book2);
//        session.save(book2);
        session.flush();
        session.close();

    }
}
