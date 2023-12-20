package book.shop.repository;

import book.shop.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("SELECT b.isbn FROM BookEntity b")
    List<String> findAllIsbns();
}
