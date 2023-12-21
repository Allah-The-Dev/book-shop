package book.shop.books;

import com.opencsv.exceptions.CsvException;
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

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<Books> search(@RequestParam(name = "search", required = false) String searchParam) {
        return ResponseEntity.ok(new Books(bookService.findBooks(searchParam)));
    }


    @PostMapping("/books")
    public ResponseEntity<String> loadBooks(@RequestParam("file") MultipartFile booksCSVFile) throws IOException,
            CsvException {
        try {
            if (bookService.saveOrUpdateBooks(booksCSVFile.getInputStream())) {
                return ResponseEntity.ok("Loaded Successfully " + booksCSVFile.getOriginalFilename());
            }
            return ResponseEntity.internalServerError().body("Error");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error occurred " + e.getMessage());
        }
    }
}
