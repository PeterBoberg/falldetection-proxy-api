package se.frost.falldetectionproxyapi.controlleradivce;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import se.frost.falldetectionproxyapi.exceptions.ApiException;
import se.frost.falldetectionproxyapi.dto.response.ErrorResponse;

@ControllerAdvice
public class ApiExceptionControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> interceptException(ApiException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getType());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }
}
