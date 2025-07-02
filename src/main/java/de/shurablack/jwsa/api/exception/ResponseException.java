package de.shurablack.jwsa.api.exception;

/**
 * Represents an exception that is thrown when an HTTP response indicates an error.
 * This exception includes the HTTP status code and an error message.
 */
public class ResponseException extends RuntimeException {

    /** The HTTP status code associated with the error. */
    private final int statusCode;

    /** The error message describing the issue. */
    private final String message;

    /**
     * Constructs a new ResponseException with the specified status code and message.
     *
     * @param statusCode The HTTP status code associated with the error.
     * @param message The error message describing the issue.
     */
    public ResponseException(int statusCode, String message) {
        super(String.format("HTTP %d: %s", statusCode, message));
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * Retrieves the HTTP status code associated with the error.
     *
     * @return The HTTP status code.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Retrieves the error message describing the issue.
     *
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }

}