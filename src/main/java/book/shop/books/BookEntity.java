package book.shop.books;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
class BookEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String isbn;
    private String bookName;

    private String description;

    private String author;

    private Integer publicationYear;

    private String smallImageUrl;

    private String largeImageUrl;

    private float price;

    private Integer numberOfAvailableBooks;

    private float rating;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getNumberOfAvailableBooks() {
        return numberOfAvailableBooks;
    }

    public void setNumberOfAvailableBooks(Integer numberOfAvailableBooks) {
        this.numberOfAvailableBooks = numberOfAvailableBooks;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public BookEntity() {
    }

    public BookEntity(Long bookId, String isbn, String bookName, String description, String author,
                      Integer publicationYear, String smallImageUrl, String largeImageUrl, float price,
                      Integer numberOfAvailableBooks, float rating) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.bookName = bookName;
        this.description = description;
        this.author = author;
        this.publicationYear = publicationYear;
        this.smallImageUrl = smallImageUrl;
        this.largeImageUrl = largeImageUrl;
        this.price = price;
        this.numberOfAvailableBooks = numberOfAvailableBooks;
        this.rating = rating;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
