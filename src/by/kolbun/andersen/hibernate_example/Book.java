package by.kolbun.andersen.hibernate_example;

public class Book {
    private Long id;
    private String title;
    private int pageCount;

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

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Book [" + Integer.toHexString(this.hashCode()) + "] id: " + id + ", title: " + title + ", pages: " + pageCount;
    }
}
