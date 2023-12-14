package book.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    private Integer bookId;
    private String name;
    private String author;

    public BookEntity(Integer bookId, String name, String author) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
    }

    public BookEntity() {
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }
}