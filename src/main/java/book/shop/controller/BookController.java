package book.shop.controller;

import book.shop.service.BookService;
import book.shop.domain.Books;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("books")
    public ResponseEntity<Books> getAllBooks() {
     return ResponseEntity.ok(new Books(bookService.allBooks()));
    }


}
