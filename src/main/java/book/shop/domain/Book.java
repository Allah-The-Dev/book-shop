package book.shop.domain;

public record Book(String isbn, String bookName, String description, String author, Integer publicationYear,
                   String smallImageUrl, String largeImageUrl, float price, Integer numberOfAvailableBooks,
                   float rating) {

}


