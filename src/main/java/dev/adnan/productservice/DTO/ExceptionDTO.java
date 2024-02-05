package dev.adnan.productservice.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class ExceptionDTO {
    String message;
    private HttpStatus errorCode;
    public ExceptionDTO(String message, HttpStatus errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

}
