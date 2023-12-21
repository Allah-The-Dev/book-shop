package book.shop.books;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification implements Specification<BookEntity> {

    @Override
    public Specification<BookEntity> and(Specification<BookEntity> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<BookEntity> or(Specification<BookEntity> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<BookEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    public static Specification<BookEntity> hasTitle(String bookName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("bookName")),
                "%" + bookName.toLowerCase() + "%");
    }

    public static Specification<BookEntity> hasAuthor(String author) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("author")),
                "%" + author.toLowerCase() + "%");
    }
}
