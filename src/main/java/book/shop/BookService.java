package book.shop;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> allBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookEntity -> new Book(bookEntity.getName(), bookEntity.getAuthor()))
                .toList();
    }

    public BookResponse save(Book book) {
        String uuid = UUID.randomUUID().toString();
        BookEntity bookEntity = bookRepository.save(new BookEntity(uuid, book.name(), book.author()));
        return new BookResponse(bookEntity.getId(), bookEntity.getName(), bookEntity.getAuthor());
    }

}
