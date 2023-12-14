package book.shop;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    public List<Book> allBooks() {
        return List.of(new Book("java", "Herb"));
    }

}
