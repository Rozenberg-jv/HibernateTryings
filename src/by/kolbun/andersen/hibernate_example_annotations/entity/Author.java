package by.kolbun.andersen.hibernate_example_annotations.entity;

import javax.persistence.*;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "status")
    private String status;

    public Author() {
    }

    public Author(String name, int age, String status) {
        this.name = name;
        this.age = age;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\t\t>" + this.getClass().getSimpleName() + " [" + Integer.toHexString(this.hashCode()) + "] id: " +
                id + ", name: " + name + ", age: " + age + ", status: " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;

        Author author = (Author) o;

        if (age != author.age) return false;
        if (!id.equals(author.id)) return false;
        if (name != null ? !name.equals(author.name) : author.name != null) return false;
        return status != null ? status.equals(author.status) : author.status == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
