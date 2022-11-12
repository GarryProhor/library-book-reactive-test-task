package librarybookreactivetesttask.service;


import librarybookreactivetesttask.model.dto.BookDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BookService {
    Mono<BookDTO> save(BookDTO dto);
    Flux<BookDTO> findAll();
    Mono<BookDTO> findById(Long id);
    Mono<BookDTO> findByTitleAndYears(String title, int years);
    Mono<Void> updateById(Long id, BookDTO dto);
    Mono<Void> deleteById(Long id);
}
