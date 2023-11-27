package by.academy.springboot.handler;

import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.exception.IncorrectParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String EXCEPTION = "exception";
    private static final String NOT_FOUND_VIEW = "notFound";

    @ExceptionHandler(value = {IncorrectParameterException.class})
    public ModelAndView handleIncorrectParameterException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(EXCEPTION, ex.getMessage());
        modelAndView.setViewName(NOT_FOUND_VIEW);
        return modelAndView;
    }

    @ExceptionHandler(value = ForbiddenActionException.class)
    public ModelAndView handlerForbiddenActionException(ForbiddenActionException ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(EXCEPTION, ex.getMessage());
        modelAndView.setViewName(NOT_FOUND_VIEW);
        return modelAndView;
    }

    @ExceptionHandler(value = HttpClientErrorException.NotFound.class)
    public ModelAndView handlerNotFoundException(HttpClientErrorException.NotFound ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(EXCEPTION, ex.getMessage());
        modelAndView.setViewName(NOT_FOUND_VIEW);
        return modelAndView;
    }
}
