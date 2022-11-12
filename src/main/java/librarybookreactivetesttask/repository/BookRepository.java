package librarybookreactivetesttask.repository;

import librarybookreactivetesttask.model.domain.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
    Mono<Book> findByTitle(String title);
    Mono<Book> findByTitleAndYears(String title, int years);
}
