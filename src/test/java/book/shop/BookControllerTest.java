package book.shop;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
public class BookControllerTest {

        @Autowired
        MockMvc mockMvc;

        @MockBean
        BookService bookService;

        @Autowired
        ObjectMapper objectMapper;

        @Test
        void shouldGetNoBooksIfNotAvailableAny() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.books").isEmpty());
        }

        @Test
        void shouldReturnBooksIfBooksAreAvailable() throws Exception {

                Book book = new Book("java", "Herbert");
                Mockito.when(bookService.allBooks())
                                .thenReturn(List.of(book));

                mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.books[0].name").value("java"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.books[0].author").value("Herbert"));

                Mockito.verify(bookService, Mockito.times(1)).allBooks();
        }

        @Test
        void shouldAddBook() throws Exception {
                Book book = new Book("Alchemist", "Paulo");
                String uuid = UUID.randomUUID().toString();
                BookResponse bookResponse = new BookResponse(uuid,
                                "Alchemist", "Paulo");
                Mockito.when(bookService.save(book)).thenReturn(bookResponse);

                mockMvc.perform(MockMvcRequestBuilders.post("/books")
                                .content(objectMapper.writeValueAsString(book)).contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isCreated())
                                .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.LOCATION,
                                                uuid))
                                .andExpect(
                                        MockMvcResultMatchers.content().string(
                                                        objectMapper.writeValueAsString(bookResponse)));

        }
}
