package book.shop;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class BookServiceTest {

    @Test
    void shouldGetDataFromDatabase() {

        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        Mockito.when(bookRepository.findAll())
                .thenReturn(List.of(new BookEntity(1, "java", "Herbert")));

        BookService bookService = new BookService(bookRepository);

        Book expectedBook = new Book("java", "Herbert");
        List<Book> books = bookService.allBooks();
        assertEquals(expectedBook, books.get(0));

        Mockito.verify(bookRepository).findAll();
    }
}