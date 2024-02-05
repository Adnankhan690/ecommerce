package dev.adnan.productservice.exceptions;

import dev.adnan.productservice.DTO.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler
    public ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException) {
        return new ResponseEntity(
                new ExceptionDTO(notFoundException.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND
        );
    }
}
