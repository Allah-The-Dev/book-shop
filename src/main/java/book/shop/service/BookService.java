package book.shop.service;

import book.shop.domain.Book;
import book.shop.domain.BookEntity;
import book.shop.repository.BookRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> allBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookEntity -> new Book(bookEntity.getBookId(), bookEntity.getIsbn(), bookEntity.getBookName(),
                        bookEntity.getDescription(), bookEntity.getAuthor(), bookEntity.getPublicationYear(),
                        bookEntity.getSmallImageUrl(), bookEntity.getLargeImageUrl(), bookEntity.getPrice(),
                        bookEntity.getNumberOfAvailableBooks(), bookEntity.getRating()))
                .toList();
    }

    public List<BookEntity> parseCsv(InputStream inputStream) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream),';')) {
            List<String[]> csvData = reader.readAll();

            return convertCsvDataToObjects(csvData);
        }
    }

    private List<BookEntity> convertCsvDataToObjects(List<String[]> csvData) {
        return csvData.stream()
                .map(row -> {
                    BookEntity csvObject = new BookEntity();
                    csvObject.setIsbn(row[0]);
                    csvObject.setBookName(row[1]);
                    csvObject.setDescription(row[2]);
                    csvObject.setAuthor(row[3]);
                    csvObject.setPublicationYear(parseInt(row[4]));
                    csvObject.setSmallImageUrl(row[5]);
                    csvObject.setLargeImageUrl(row[6]);
                    csvObject.setPrice(parseFloat(row[7]));
                    csvObject.setNumberOfAvailableBooks(parseInt(row[8]));
                    csvObject.setRating(parseFloat(row[9]));

                    return csvObject;
                })
                .toList();
    }

    public boolean saveOrUpdateBooks(InputStream inputStream) throws IOException, CsvException {
        logger.info("Incoming stream service ==== "+inputStream);
        List<BookEntity> bookList = parseCsv(inputStream);
        return !bookRepository.saveAll(bookList).isEmpty();
    }
}
