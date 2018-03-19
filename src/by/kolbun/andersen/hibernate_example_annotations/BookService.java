package by.kolbun.andersen.hibernate_example_annotations;

import by.kolbun.andersen.hibernate_example_annotations.dao.BookDao;
import by.kolbun.andersen.hibernate_example_annotations.entity.Author;
import by.kolbun.andersen.hibernate_example_annotations.entity.Book;

import java.util.ArrayList;
import java.util.List;


public class BookService {
    private BookDao dao = new BookDao();


    public void clearTables() {
        dao.clearTables();
    }

    public void insertData(int i) {
        dao.insertData(i);
    }

    public List<Book> showTable() {
        return dao.getAll();
    }

    public void insertRecord(String[] data, String[][] aut_data) {
        List l = new ArrayList();
        for (String[] s : aut_data)
            l.add(new Author(s[0], Integer.parseInt(s[1]), s[2]));
        Book b = new Book(data[0], Integer.parseInt(data[1]), l);
        dao.add(b);
    }

    public void updateById(String[] data, String val) {
        Book b = dao.get(Integer.parseInt(data[0]));
        switch (data[1]) {
            case "title":
                b.setTitle(val);
                break;
            case "ISBN":
            case "isbn":
                b.setISBN(Integer.parseInt(val));
                break;
        }
        dao.flush();
    }

    public void updateById(String[] data, String[] val, int aId) {
        Book b = dao.get(Integer.parseInt(data[0]));
        Author a2Ins = new Author(val[0], Integer.parseInt(val[1]), val[2]);
        Author a2Del = null;
        for (Author at : b.getAuthors())
            if (at.getId() == aId) {
                a2Del = at;
                break;
            }
        if (a2Del != null)
            b.getAuthors().remove(a2Del);

        b.getAuthors().add(a2Ins);
    }


    public void deleteById(int id) {
        dao.delete(id);
    }


    public String getGeneralReport() {
        return dao.getGeneralReport();
    }

    public void finish() {
        dao.finish();
    }

    public void addAuthor(int id, String[] s) {
        Book b = dao.get(id);
        b.getAuthors().add(new Author(s[0], Integer.parseInt(s[1]), s[2]));
        dao.flush();
    }
}
