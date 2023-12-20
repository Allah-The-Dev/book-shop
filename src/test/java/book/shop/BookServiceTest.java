package book.shop;

import book.shop.domain.Book;
import book.shop.domain.BookEntity;
import book.shop.repository.BookRepository;
import book.shop.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    BookRepository bookRepository;
    BookService bookService;

    @BeforeEach
    void setup(){
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }
    @Test
    public void shouldReturnBookListFromDB() {

        BookEntity bookEntity = new BookEntity(1L, "01234X", "Java Book", "Book description",
                "Book author", 2023, "imageurl/img.img",
                "largeImageurl.img", 100.55F, 1, 4.5F);

        BookEntity bookEntity2 = new BookEntity(2L, "01235X", "TDD Book", "AMAR book",
                "Amar", 2022, "imageurl/img.img",
                "largeImageurl.img", 105.55F, 2, 5.0F);

        Book book = new Book(1L, "01234X", "Java Book", "Book description",
                "Book author", 2023, "imageurl/img.img",
                "largeImageurl.img", 100.55F, 1, 4.5F);
        Book book2 = new Book(2L, "01235X", "TDD Book", "AMAR book",
                "Amar", 2022, "imageurl/img.img",
                "largeImageurl.img", 105.55F, 2, 5.0F);

        List<BookEntity> bookEntityList = new ArrayList<>(List.of(bookEntity2, bookEntity));
        List<Book> expectedResponse = new ArrayList<>(List.of(book, book2));

        //mock
        Mockito.when(bookRepository.findAll()).thenReturn(bookEntityList);

        List<Book> actualResponse = bookService.allBooks();


        //verification
        assertTrue((expectedResponse.size() == actualResponse.size()
                && expectedResponse.containsAll(actualResponse)));
        Mockito.verify(bookRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void shouldReturnEmptyBookListFromDBIfNotPresent() {
        Mockito.when(bookRepository.findAll()).thenReturn(Collections.emptyList());
        List<Book> actualResponse = bookService.allBooks();

        assertTrue(0 == actualResponse.size());

    }

    @Test
    public void returnsTrueWhenRepsoitoryReturnsTrue() throws Exception {
        BookEntity book1 = getBookEntity1();
        when(bookRepository.saveAll(Mockito.any())).thenReturn(List.of(book1));
        InputStream stream = new ByteArrayInputStream(getCSV());
        assertTrue(bookService.saveOrUpdateBooks(stream));
    }

    private byte[] getCSV(){
        return "0195153448;Classical Mythology;Classical Mythology;Mark P. O. Morford;2002;http://images.amazon.com/images/P/0195153448.01.MZZZZZZZ.jpg;http://images.amazon.com/images/P/0195153448.01.LZZZZZZZ.jpg;256.22242641102025;0;0.0\n0002005018;Clara Callan;Clara Callan;Richard Bruce Wright;2001;http://images.amazon.com/images/P/0002005018.01.MZZZZZZZ.jpg;http://images.amazon.com/images/P/0002005018.01.LZZZZZZZ.jpg;135.3421165008236;9;4.928571428571429".getBytes();
    }

    private BookEntity getBookEntity1(){
        return  new BookEntity(1L, "01234X", "Java Book", "Book description",
                "Book author", 2023, "imageurl/img.img",
                "largeImageurl.img", 100.55F, 1, 4.5F);
    }

}