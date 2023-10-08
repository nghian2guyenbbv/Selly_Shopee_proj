package selly.authenticate.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import selly.authenticate.login.service.exception.SellyLoginException;

@ControllerAdvice
public class RestExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SellyLoginException.class)
    @ResponseBody
    public ErrorExceptionDto handleNotFoundSellyUser(SellyLoginException ex) {
        return ErrorExceptionDto.builder().errorCode(ex.getErrorCode()).message(ex.getMessage()).build();
    }

}
