package librarybookreactivetesttask.service.impl;

import librarybookreactivetesttask.builder.BookBuilder;
import librarybookreactivetesttask.exception.BookAlreadyExistException;
import librarybookreactivetesttask.exception.BookNotFoundException;
import librarybookreactivetesttask.model.domain.Book;
import librarybookreactivetesttask.model.dto.BookDTO;
import librarybookreactivetesttask.repository.BookRepository;
import librarybookreactivetesttask.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookBuilder builder;
    private final BookRepository repository;

    @Override
    public Mono<BookDTO> save(BookDTO dto) {
        return repository.findByTitle(dto.getTitle())
                .flatMap(__ -> Mono.error(new BookAlreadyExistException("Book with title - {0}, already exist", dto.getTitle())))
                .switchIfEmpty(repository.save(builder.build(dto)))
                .cast(Book.class)
                .flatMap(o -> Mono.just(builder.build(o)));
    }

    @Override
    public Flux<BookDTO> findAll() {
        return repository.findAll()
                .flatMap(o -> Mono.just(builder.build(o)));
    }

    @Override
    public Mono<BookDTO> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error( new BookNotFoundException("Book with id - {0}, not found", id)))
                .flatMap(o -> Mono.just(builder.build(o)));
    }

    @Override
    public Mono<BookDTO> findByTitleAndYears(String title, int years) {
        return repository.findByTitleAndYears(title, years)
                .switchIfEmpty(Mono.error(new BookNotFoundException("Book with title - {0} and years {1}, not found", title, years)))
                .flatMap(o -> Mono.just(builder.build(o)));
    }

    @Override
    public Mono<Void> updateById(Long id, BookDTO dto) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new BookNotFoundException("Book with id - {0}, not found", id)))
                .flatMap(o -> Mono.just(builder.build(dto, o)))
                .flatMap(repository::save)
                .flatMap(__ -> Mono.empty());
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return findById(id)
                .flatMap(__ -> repository.deleteById(id));
    }
}
