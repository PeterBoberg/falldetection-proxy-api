package se.frost.falldetectionproxyapi.dto;

import se.frost.falldetectionproxyapi.exceptions.ExceptionType;

public class ErrorResponse {

    private final ExceptionType exceptionType;
    private final String message;

    public ErrorResponse(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        this.message = exceptionType.getMessage();
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public String getMessage() {
        return message;
    }
}
