package book.shop.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("SELECT b.isbn FROM BookEntity b")
    List<String> findAllIsbns();
}
