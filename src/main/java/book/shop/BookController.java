package book.shop;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("books")
    public ResponseEntity<Books> getAllBooks() {
        return ResponseEntity.ok(new Books(bookService.allBooks()));
    }

    @PostMapping("books")
    public ResponseEntity<BookResponse> saveBook(@RequestBody Book book) {
        BookResponse savedBook = bookService.save(book);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, savedBook.id());
        
        return new ResponseEntity<BookResponse>(savedBook, httpHeaders, HttpStatus.CREATED);
    }

}
