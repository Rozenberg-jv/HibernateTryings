package by.kolbun.andersen.hibernate_example.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book_info")
public class BookInfo implements Serializable {

    @Id
    @GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "info"))
    @GeneratedValue(generator = "gen")
    private Long id;
    @Column(name = "page_count")
    private int pageCount;
    @Column(name = "format")
    private String format;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + Integer.toHexString(this.hashCode()) + "] id: "
                + id + ", pages: " + pageCount + ", format: " + format
                + ", book [" + (book != null ? Integer.toHexString(book.hashCode()) : "null") + "]";

    }
}
