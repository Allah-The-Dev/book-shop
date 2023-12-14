package book.shop;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class BookController {

    @GetMapping("books")
    public List<String> books() {
        return Collections.emptyList();
    }
}
