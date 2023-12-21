package book.shop.books;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    public void returnsSuccessfulWhenLoaded() throws Exception {

        MockMultipartFile file = new MockMultipartFile(
                "file", "test.csv", "text/csv", "1,John Doe,25".getBytes());
        when(bookService.saveOrUpdateBooks(Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/books")
                        .file(file))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Loaded Successfully test.csv"))
                .andDo(print());
    }

    @Test
    public void returnsSuccessfulWhenServiceReturnsTrue() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.csv", "text/csv", "1,John Doe,25".getBytes());
        when(bookService.saveOrUpdateBooks(Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.multipart("/books")
                        .file(file))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Loaded Successfully test.csv"))
                .andDo(print());

        Mockito.verify(bookService, Mockito.times(1)).saveOrUpdateBooks(Mockito.any());
    }

    @Test
    public void returnsInternalServerErrorWhenServiceReturnsFalse() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.csv", "text/csv", "1,John Doe,25".getBytes());
        when(bookService.saveOrUpdateBooks(Mockito.any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.multipart("/books")
                        .file(file))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Error"))
                .andDo(print());

        Mockito.verify(bookService, Mockito.times(1)).saveOrUpdateBooks(Mockito.any());
    }

    @Test
    public void shouldGetBookListBySearchAuthorName() throws Exception {
        Book book = new Book(1L, "01234X", "Java book", "Book description",
                "Amar", 2023, "imageUrl/img.img",
                "largeImageUrl.img", 100.55F, 1, 4.5F);

        List<Book> bookList = new ArrayList<>(List.of(book));

        when(bookService.findBooks("Java")).thenReturn(bookList);

        mockMvc.perform(MockMvcRequestBuilders.get("/books?search=Java")
                        .param("authorName", "Amar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[0].author")
                        .value("Amar"));

    }


}
