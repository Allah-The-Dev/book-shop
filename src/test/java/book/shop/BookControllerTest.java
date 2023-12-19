package book.shop;

import book.shop.controller.BookController;
import book.shop.domain.Book;
import book.shop.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    void shouldGetNoBooksIfNotAvailableAny() throws Exception {
        when(bookService.allBooks()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.books").isEmpty());
    }

    @Test
    void shouldGetBooksIfAvailable() throws Exception {

        Book book = new Book("01234X", "Java book", "Book description",
                "Amar", 2023, "imageUrl/img.img",
                "largeImageUrl.img", 100.55F, 1, 4.5F);


        when(bookService.allBooks()).thenReturn(List.of(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[0].bookName")
                        .value("Java book"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[0].author")
                        .value("Amar"));

    }


}
