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
    private Session session = HibernateUtil.getSession();
    private Transaction transaction;

    @Override
    public int add(Book t) {
        transaction = getTransaction();
        int id = (int) session.save(t);
        System.out.println("add()    -> " + printTransInfo());
        transaction.commit();
        return id;
    }

    @Override
    public Book get(int id) {
        transaction = getTransaction();
        Criteria crit = session.createCriteria(Book.class);
        crit.add(Restrictions.eq("id", id));
        Book result = (Book) crit.uniqueResult();
        System.out.println("get() -> " + printTransInfo());
        transaction.commit();
        return result;
    }

    @Override
    public List<Book> getAll() {
        List<Book> result;
        transaction = getTransaction();
        Criteria crit = session.createCriteria(Book.class);
        result = crit.list();
        System.out.println("getAll() -> " + printTransInfo());
        transaction.commit();
        return result;
    }

    /*@Override
    public void update(Book t) {
        transaction = getTransaction();
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
    public void update(Book t) {
        transaction = getTransaction();
        session.update(t);
        System.out.println("update() -> " + printTransInfo());
        transaction.commit();
    }

    @Override
    public void delete(int id) {
        transaction = getTransaction();
        Book b = (Book) session.load(Book.class, id);
        session.delete(b);
        System.out.println("delete() -> " + printTransInfo());
        transaction.commit();
    }

    @Override
    public void finish() {
        if (session != null && !session.isOpen()) session.close();
    }

    private Transaction getTransaction() {
        if (transaction == null || !transaction.isActive()) return session.beginTransaction();
        return transaction;
    }

    private String printTransInfo() {
        return " > info > transaction: " + Integer.toHexString(transaction.hashCode());
    }

    //

    public List<Author> getAllAuthors() {
        transaction = getTransaction();
        Criteria crit = session.createCriteria(Author.class);
        List<Author> result = crit.list();
        transaction.commit();
        return result;
    }

    public void clearTables() {
        transaction = getTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM `books_authors`;");
        query.executeUpdate();
        query = session.createSQLQuery("DELETE FROM `authors`;");
        query.executeUpdate();
        query = session.createSQLQuery("DELETE FROM `books`;");
        query.executeUpdate();
        transaction.commit();
    }

    public void insertData(int n) {
//        transaction = getTransaction();
        for (int i = 0; i < n; i++) {
            List l = new ArrayList();
            l.add(new Author("name" + i, 20, "status" + i));
            Book book = new Book("title" + i, 12340 + i, l);
            add(book);
            System.out.println("added: " + book);
        }
//        transaction.commit();
    }

    public void flush() {
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
}
