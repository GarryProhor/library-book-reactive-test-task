package librarybookreactivetesttask.exception;

import java.text.MessageFormat;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String pattern, Object... args) {
        super(new MessageFormat(pattern).format(args));
    }
}
