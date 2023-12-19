package book.shop;

import book.shop.domain.Book;
import book.shop.domain.BookEntity;
import book.shop.repository.BookRepository;
import book.shop.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class BookServiceTest {



    @Test
    public void shouldReturnBookListFromDB() {

       BookRepository bookRepository = Mockito.mock(BookRepository.class);

        BookService bookService = new BookService(bookRepository);

        BookEntity bookEntity = new BookEntity("01234X", "Java Book", "Book description",
                "Book author", 2023, "imageurl/img.img",
                "largeImageurl.img", 100.55F, 1, 4.5F);

        BookEntity bookEntity2 = new BookEntity("01235X", "TDD Book", "AMAR book",
                "Amar", 2022, "imageurl/img.img",
                "largeImageurl.img", 105.55F, 2, 5.0F);

        Book book = new Book("01234X", "Java Book", "Book description",
                "Book author", 2023, "imageurl/img.img",
                "largeImageurl.img", 100.55F, 1, 4.5F);
        Book book2 = new Book("01235X", "TDD Book", "AMAR book",
                "Amar", 2022, "imageurl/img.img",
                "largeImageurl.img", 105.55F, 2, 5.0F);

        List<BookEntity> bookEntityList = new ArrayList<>(List.of(bookEntity2, bookEntity));
        List<Book> expectedResponse = new ArrayList<>(List.of(book, book2));

        //mock
        Mockito.when(bookRepository.findAll()).thenReturn(bookEntityList);

        List<Book> actualResponse = bookService.allBooks();


        //verification
        assertTrue("true", expectedResponse.size() == actualResponse.size()
                && expectedResponse.containsAll(actualResponse));

        Mockito.verify(bookRepository,Mockito.times(1)).findAll();

    }

    @Test
    public void shouldReturnEmptyBookListFromDBIfNotPresent() {

        BookRepository bookRepository = Mockito.mock(BookRepository.class);

        Mockito.when(bookRepository.findAll()).thenReturn(Collections.emptyList());

        BookService bookService = new BookService(bookRepository);

        List<Book> actualResponse = bookService.allBooks();

        assertTrue("true", 0 == actualResponse.size());

    }

}