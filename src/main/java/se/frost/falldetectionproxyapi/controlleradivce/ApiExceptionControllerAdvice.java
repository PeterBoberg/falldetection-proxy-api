package se.frost.falldetectionproxyapi.controlleradivce;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import se.frost.falldetectionproxyapi.dto.response.ErrorResponse;
import se.frost.falldetectionproxyapi.exceptions.ApiException;
import se.frost.falldetectionproxyapi.exceptions.ExceptionType;

import javax.servlet.annotation.ServletSecurity;
import java.util.ResourceBundle;

@ControllerAdvice
public class ApiExceptionControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> interceptApiException(ApiException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getType());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }
}
