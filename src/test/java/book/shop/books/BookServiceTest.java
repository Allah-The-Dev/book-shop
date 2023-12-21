package book.shop.books;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class BookServiceTest {


    @Autowired
    ModelMapper modelMapper;

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void ShouldGetAllBooksWhenNoParam() {

        when(bookRepository.findAll()).thenReturn(List.of(getTestBookEntity()));

        List<Book> resultWithSearchParam = bookService.findBooks(null);

        assertEquals(1, resultWithSearchParam.size());

        List<Book> resultWithoutSearchParam = bookService.findBooks(null);

        assertEquals(1, resultWithoutSearchParam.size());

    }

    @Test
    void ShouldGetSpecificBooksWhenParamPassed() {

        String searchParam = "Amar";

        when(bookRepository.findAll(Mockito.any(Specification.class))).thenReturn(List.of(getTestBookEntity()));

        List<Book> resultWithSearchParam = bookService.findBooks(searchParam);

        assertEquals(1, resultWithSearchParam.size());

        List<Book> resultWithoutSearchParam = bookService.findBooks(searchParam);

        assertEquals(1, resultWithoutSearchParam.size());

    }


    private Book getTestBook() {
        return new Book(2L, "01235X", "TDD Book", "AMAR book",
                "Amar", 2022, "imageurl/img.img",
                "largeImageurl.img", 105.55F, 2, 5.0F);
    }

    private BookEntity getTestBookEntity() {
        return new BookEntity(2L, "01235X", "TDD Book", "AMAR book",
                "Amar", 2022, "imageurl/img.img",
                "largeImageurl.img", 105.55F, 2, 5.0F);
    }


    @Test
    public void returnsTrueWhenRepsoitoryReturnsTrue() throws Exception {
        BookEntity book1 = getTestBookEntity();
        when(bookRepository.saveAll(Mockito.any())).thenReturn(List.of(book1));
        InputStream stream = new ByteArrayInputStream(getCSV());
        assertTrue(bookService.saveOrUpdateBooks(stream));
    }

    private byte[] getCSV(){
        return "0195153448;Classical Mythology;Classical Mythology;Mark P. O. Morford;2002;http://images.amazon.com/images/P/0195153448.01.MZZZZZZZ.jpg;http://images.amazon.com/images/P/0195153448.01.LZZZZZZZ.jpg;256.22242641102025;0;0.0\n0002005018;Clara Callan;Clara Callan;Richard Bruce Wright;2001;http://images.amazon.com/images/P/0002005018.01.MZZZZZZZ.jpg;http://images.amazon.com/images/P/0002005018.01.LZZZZZZZ.jpg;135.3421165008236;9;4.928571428571429".getBytes();
    }



}