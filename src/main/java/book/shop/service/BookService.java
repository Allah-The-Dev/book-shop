package book.shop.service;

import book.shop.domain.Book;
import book.shop.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> allBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookEntity -> new Book(bookEntity.getIsbn(), bookEntity.getBookName(), bookEntity.getDescription(),
                        bookEntity.getAuthor(), bookEntity.getPublicationYear(), bookEntity.getSmallImageUrl(),
                        bookEntity.getLargeImageUrl(), bookEntity.getPrice(), bookEntity.getNumberOfAvailableBooks(),
                        bookEntity.getRating()))
                .toList();
    }
}
