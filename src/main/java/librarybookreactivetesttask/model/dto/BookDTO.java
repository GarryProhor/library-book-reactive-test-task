package librarybookreactivetesttask.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDTO {

    @Nullable
     Long id;

    @NotEmpty
    @Size(max = 20)
    @JsonProperty("title")
     String title;

    @NotEmpty
    @JsonProperty("author")
     String author;

    @NotEmpty
    @JsonProperty("years")
     int years;

    @Builder
    public BookDTO(String title, int years, String author) {
        this.title = title;
        this.author = author;
        this.years = years;
    }
}
