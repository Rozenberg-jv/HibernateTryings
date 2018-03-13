package by.kolbun.andersen.hibernate_example.entity;

public class Book {
    private Long id;
    private String title;
    private int ISBN;
    private BookInfo info;

    public BookInfo getInfo() {
        return info;
    }

    public void setInfo(BookInfo info) {
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + Integer.toHexString(this.hashCode()) + "] id: "
                + id + ", title: " + title + ", pages: " + ISBN;
    }
}
