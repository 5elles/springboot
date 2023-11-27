package by.academy.springboot.handler;

import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.exception.IncorrectParameterException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IncorrectParameterException.class})
    public ModelAndView handleIncorrectParameterException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex.getMessage());
        modelAndView.setViewName("notFound");
        return modelAndView;
    }

    @ExceptionHandler(value = ForbiddenActionException.class)
    public ModelAndView handlerForbiddenActionException(ForbiddenActionException ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex.getMessage());
        modelAndView.setViewName("notFound");
        return modelAndView;
    }

    @ExceptionHandler(value = HttpClientErrorException.NotFound.class)
    public ModelAndView handlerNotFoundException(HttpClientErrorException.NotFound ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex.getMessage());
        modelAndView.setViewName("notFound");
        return modelAndView;
    }

    @ExceptionHandler(value = LoginException.class)
    public ModelAndView handlerLoginException(LoginException ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex.getMessage());
        modelAndView.setViewName("notFound");
        return modelAndView;
    }

    @ExceptionHandler(value = {SQLException.class})
    public void handleSQLException(Exception ex) {
        logger.error("SQL", ex);
        throw new IncorrectParameterException("sql: check the parameters");
    }



}
