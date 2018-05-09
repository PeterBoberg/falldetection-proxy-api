package se.frost.falldetectionproxyapi.exceptions;

public enum ExceptionType {
    RESOURCE_NOT_FOUND("The resource could not be found"),
    INVALID_CREDENTIALS("Invalid username, password or token");

    private String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
