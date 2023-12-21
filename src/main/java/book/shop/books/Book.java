package book.shop.books;

import java.util.Objects;

public class Book {

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

    public Book(Long bookId, String isbn, String bookName, String description, String author, Integer publicationYear, String smallImageUrl, String largeImageUrl, float price, Integer numberOfAvailableBooks, float rating) {
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

    public Book() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (Float.compare(book.price, price) != 0) return false;
        if (Float.compare(book.rating, rating) != 0) return false;
        if (!Objects.equals(bookId, book.bookId)) return false;
        if (!Objects.equals(isbn, book.isbn)) return false;
        if (!Objects.equals(bookName, book.bookName)) return false;
        if (!Objects.equals(description, book.description)) return false;
        if (!Objects.equals(author, book.author)) return false;
        if (!Objects.equals(publicationYear, book.publicationYear))
            return false;
        if (!Objects.equals(smallImageUrl, book.smallImageUrl))
            return false;
        if (!Objects.equals(largeImageUrl, book.largeImageUrl))
            return false;
        return Objects.equals(numberOfAvailableBooks, book.numberOfAvailableBooks);
    }

    @Override
    public int hashCode() {
        int result = bookId != null ? bookId.hashCode() : 0;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publicationYear != null ? publicationYear.hashCode() : 0);
        result = 31 * result + (smallImageUrl != null ? smallImageUrl.hashCode() : 0);
        result = 31 * result + (largeImageUrl != null ? largeImageUrl.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (numberOfAvailableBooks != null ? numberOfAvailableBooks.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        return result;
    }
}


