package br.com.barbershop.exception;

import br.com.barbershop.facade.MessageFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppBussinessExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            AppBusinessException.class
    })
    protected ResponseEntity<Object> handleInvalidInput(Exception ex) {
        return ResponseEntity
                .badRequest()
                .body(new MessageFacade(ex.getMessage()));
    }
}
