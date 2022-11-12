package librarybookreactivetesttask.exception;

import java.text.MessageFormat;

public class BookAlreadyExistException extends RuntimeException{
    public BookAlreadyExistException(String pattern, Object... args) {
        super(new MessageFormat(pattern).format(args));
    }
}
