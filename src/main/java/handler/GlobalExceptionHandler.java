package handler;

import by.academy.springboot.exception.IncorrectParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final String NOT_FOUND = "/src/main/webapp/WEB-INF/jsp/notFound.jsp";

    @ExceptionHandler(value = {IncorrectParameterException.class})
    public ModelAndView handleIncorrectParameterException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex.getMessage());
        modelAndView.setViewName("notFound");
        ex.printStackTrace();
//        model.addAttribute("message", ex.getMessage());
        return modelAndView;
//       return NOT_FOUND;
    }
}
