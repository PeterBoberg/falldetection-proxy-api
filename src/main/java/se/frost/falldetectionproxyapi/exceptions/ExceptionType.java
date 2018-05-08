package se.frost.falldetectionproxyapi.exceptions;

public enum ExceptionType {
    RESOURCE_NOT_FOUND("The resource could not be found");

    private String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
