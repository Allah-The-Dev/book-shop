package book.shop.books;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }


    public List<BookEntity> parseCsv(InputStream inputStream) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream), ';')) {
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
        logger.info("Incoming stream service ==== " + inputStream);
        List<BookEntity> bookList = parseCsv(inputStream);
        return !bookRepository.saveAll(bookList).isEmpty();
    }

    public List<Book> findBooks(String searchParam) {

        ModelMapper modelMapper = new ModelMapper();

        if (StringUtils.hasText(searchParam))
            return bookRepository.findAll(Specification.where(BookSpecification.hasAuthor(searchParam)).
                            or(Specification.where(BookSpecification.hasTitle(searchParam))))
                    .stream()
                    .map(bookEntity -> modelMapper.map(bookEntity, Book.class))
                    .toList();
        else
            return bookRepository.findAll()
                    .stream()
                    .map(bookEntity -> modelMapper.map(bookEntity, Book.class))
                    .toList();

    }

}
