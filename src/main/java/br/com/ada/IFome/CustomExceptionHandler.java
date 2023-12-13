package br.com.ada.IFome;

import br.com.ada.IFome.exceptions.BusinessException;
import br.com.ada.IFome.exceptions.RestauranteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = RestauranteNotFoundException.class)
    public ResponseEntity<String> captureRestauranteNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<Void> captureBusinessException() {
        return ResponseEntity.badRequest().build();
    }

}
