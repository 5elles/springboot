package handler;

import by.academy.springboot.exception.IncorrectParameterException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final String NOT_FOUND = "/src/main/webapp/WEB-INF/jsp/notFound.jsp";

    @ExceptionHandler(IncorrectParameterException.class)
    public String handleIncorrectParameterException(IncorrectParameterException ex, Model model) {
        ex.printStackTrace();
        model.addAttribute("message", ex.getMessage());
       return NOT_FOUND;
    }
}
