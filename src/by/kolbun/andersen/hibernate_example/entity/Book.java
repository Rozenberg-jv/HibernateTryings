package by.kolbun.andersen.hibernate_example.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books")
public class Book implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK")
//    @SequenceGenerator(name = "PK", sequenceName = "sequence_books")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Column(name = "ISBN", nullable = false)
    private int ISBN;
    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
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
                + id + ", title: " + title + ", ISBN: " + ISBN + ". Info: " + info;
    }
}
