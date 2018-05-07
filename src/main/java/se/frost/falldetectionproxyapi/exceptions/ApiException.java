package se.frost.falldetectionproxyapi.exceptions;

public class ApiException extends RuntimeException {

    private final ExceptionType type;

    public ApiException(ExceptionType type) {
        super(type.getMessage());
        this.type = type;
    }
}
