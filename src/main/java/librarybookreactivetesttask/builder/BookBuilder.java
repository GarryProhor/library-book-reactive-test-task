package librarybookreactivetesttask.builder;

import librarybookreactivetesttask.model.domain.Book;
import librarybookreactivetesttask.model.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookBuilder {
    private final ModelMapper modelMapper;

    public BookDTO build(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public Book build(BookDTO dto) {
        return modelMapper.map(dto, Book.class);
    }

    public Book build(BookDTO dto, Book book) {
        modelMapper.map(dto, book);
        return book;
    }
}
