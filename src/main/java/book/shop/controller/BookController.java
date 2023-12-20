package book.shop.controller;

import book.shop.service.BookService;
import book.shop.domain.Books;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<Books> getAllBooks() {
     return ResponseEntity.ok(new Books(bookService.allBooks()));
    }

    @PostMapping("/books")
    public ResponseEntity<String> loadBooks(@RequestParam("file") MultipartFile booksCSVFile) throws IOException, CsvException {
        logger.info("Incoming stream controller ==== "+booksCSVFile.getInputStream());

        if(bookService.saveBooks(booksCSVFile.getInputStream())){
            return ResponseEntity.ok("Loaded Succesfully "+booksCSVFile.getOriginalFilename());
        }
        return ResponseEntity.internalServerError().body("Error");
    }
}
