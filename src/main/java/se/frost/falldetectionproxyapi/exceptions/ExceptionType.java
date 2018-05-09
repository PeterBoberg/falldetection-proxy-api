package se.frost.falldetectionproxyapi.exceptions;

public enum ExceptionType {
    RESOURCE_NOT_FOUND("The resource could not be found"),
    BAD_CREDENTIALS("Invalid username, password or token"),
    UNAUTHORIZED("Full authentication is needed to access this resource");

    private String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
