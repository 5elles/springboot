package by.academy.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IncorrectParameterException extends RuntimeException {
    public IncorrectParameterException(String errorMessage) {
        super(errorMessage);
    }
}
