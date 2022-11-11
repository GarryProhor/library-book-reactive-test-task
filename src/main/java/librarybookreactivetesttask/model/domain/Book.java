package librarybookreactivetesttask.model.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("book")
public class Book {

    @Id
    Long id;

    @Column(value = "title")
    String title;

    @Column(value = "author")
    String author;

    @Column(value = "years")
    int years;

    @Builder
    public Book(Long id, String title, String author, int years) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.years = years;
    }
}
