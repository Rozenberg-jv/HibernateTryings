package by.kolbun.andersen.hibernate_example_annotations.dao;


import by.kolbun.andersen.hibernate_example_annotations.HibernateUtil;
import by.kolbun.andersen.hibernate_example_annotations.entity.Author;
import by.kolbun.andersen.hibernate_example_annotations.entity.Book;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class BookDao implements IDao {
    private static BookDao daoInstance = new BookDao();
    private Session session = HibernateUtil.getSession();
    private Transaction transaction;

    public static BookDao getDaoInstance() {
        return daoInstance;
    }

    @Override
    public synchronized int add(Book t) {
        transaction = session.beginTransaction();
        int id = (int) session.save(t);
//        System.out.println("add()    -> " + printTransInfo());
        transaction.commit();
        return id;
    }

    @Override
    public synchronized Book get(int id) {
        transaction = session.beginTransaction();
        Criteria crit = session.createCriteria(Book.class);
        crit.add(Restrictions.eq("id", id));
        Book result = (Book) crit.uniqueResult();
//        System.out.println("get() -> " + printTransInfo());
        transaction.commit();
        return result;
    }

    @Override
    public synchronized List<Book> getAll() {
        List<Book> result;
        transaction = session.beginTransaction();
        Criteria crit = session.createCriteria(Book.class);
        result = crit.list();
//        System.out.println("getAll() -> " + printTransInfo());
        transaction.commit();
        return result;
    }

    /*@Override
    public void update(Book t) {
        transaction = session.beginTransaction();
        Book b = (Book) session.get(Book.class, t.getId());
        b.getAuthors().clear();
        b.getAuthors().addAll(t.getAuthors());
        b.setTitle(t.getTitle());
        b.setISBN(t.getISBN());
        session.update(b);
        System.out.println("update() -> " + printTransInfo());
        transaction.commit();
    }*/

    @Override
    public synchronized void update(Book t) {
        transaction = session.beginTransaction();
        session.update(t);
//        System.out.println("update() -> " + printTransInfo());
        transaction.commit();
    }

    @Override
    public synchronized void delete(int id) {
        transaction = session.beginTransaction();
        Book b = (Book) session.load(Book.class, id);
        session.delete(b);
//        System.out.println("delete() -> " + printTransInfo());
        transaction.commit();
    }

    @Override
    public synchronized void finish() {
        if (session != null && !session.isOpen()) session.close();
    }

    /*private Transaction getTransaction() {
        if (transaction == null || !transaction.isActive()) return session.beginTransaction();
        return transaction;
    }*/

    private String printTransInfo() {
        return " > info > transaction: " + Integer.toHexString(transaction.hashCode());
    }

    //

    public synchronized List<Author> getAllAuthors() {
        transaction = session.beginTransaction();
        Criteria crit = session.createCriteria(Author.class);
        List<Author> result = crit.list();
        transaction.commit();
        return result;
    }

    public synchronized void clearTables() {
        transaction = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM `books_authors`;");
        query.executeUpdate();
        query = session.createSQLQuery("DELETE FROM `authors`;");
        query.executeUpdate();
        query = session.createSQLQuery("DELETE FROM `books`;");
        query.executeUpdate();
        transaction.commit();
    }

    public synchronized void insertData(int n) {
//        transaction = session.beginTransaction();
        for (int i = 0; i < n; i++) {
            List l = new ArrayList();
            l.add(new Author("name" + i, 20, "status" + i));
            Book book = new Book("title" + i, 12340 + i, l);
            add(book);
            System.out.println("added: " + book);
        }
//        transaction.commit();
    }

    public synchronized void flush() {
        session.flush();
    }

    public String getGeneralReport() {
        StringBuilder sb = new StringBuilder();

        sb.append(">>\t General report <<\n");
        Criteria crit = session.createCriteria(Book.class);
        crit.setProjection(Projections.rowCount());
        sb.append(">>\t Count of books: ").append(crit.list().get(0)).append("\n");
        Criteria crit2 = session.createCriteria(Author.class);
        crit2.setProjection(Projections.distinct(Projections.property("status")));
        List l = crit2.list();
        sb.append(">>\t List of authors statuses: \n");
        for (Object o : l)
            sb.append(">>\t").append(o).append("\n");
        Criteria crit3 = session.createCriteria(Author.class);
        crit3.setProjection(Projections.avg("age"));
        sb.append(">>\t Average age of authors: " + crit3.list().get(0));

        return sb.toString();
    }

    public List getListOfBooksIds() {
        Criteria crit = session.createCriteria(Book.class);
        crit.setProjection(Projections.property("id"));
        return crit.list();
    }
}
