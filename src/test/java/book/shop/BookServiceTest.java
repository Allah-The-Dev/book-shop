package book.shop;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

public class BookServiceTest {

    @Test
    void shouldGetDataFromDatabase() {

        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        Mockito.when(bookRepository.findAll())
                .thenReturn(List.of(new BookEntity("1", "java", "Herbert")));

        BookService bookService = new BookService(bookRepository);

        Book expectedBook = new Book("java", "Herbert");
        List<Book> books = bookService.allBooks();
        assertEquals(expectedBook, books.get(0));

        Mockito.verify(bookRepository).findAll();
    }

    @Test
    void shouldSaveBookInBookTable() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);

        String uuid = UUID.randomUUID().toString();
        BookEntity bookEntity = new BookEntity(uuid, "python", "jamie");

        Mockito.when(bookRepository.save(Mockito.any(BookEntity.class))).thenReturn(bookEntity);

        BookService bookService = new BookService(bookRepository);

        Book book = new Book("python", "jamie");
        BookResponse bookResponse = bookService.save(book);

        assertEquals(bookResponse, new BookResponse(uuid, "python", "jamie"));
        Mockito.verify(bookRepository).save(Mockito.any(BookEntity.class));
    }
}