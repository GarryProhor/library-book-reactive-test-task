package librarybookreactivetesttask.controller;


import librarybookreactivetesttask.model.dto.BookDTO;
import librarybookreactivetesttask.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("books")
public class BookController {

    private final BookService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<?> create(@Valid @RequestBody BookDTO dto) {
        return service.save(dto);
    }

    @GetMapping("{id}")
    public Mono<?> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public ResponseEntity<?> find(String title) {
        return Objects.nonNull(title)
                ? ResponseEntity.ok(service.findByTitle(title))
                : ResponseEntity.ok(service.findAll());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<?> update(@PathVariable Long id, @Valid @RequestBody BookDTO dto) {
        return service.updateById(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<?> delete(@PathVariable Long id) {
        return service.deleteById(id);
    }
}
