package net.square.exceptions;

/**
 * Custom exception class for handling proxy-related errors.
 * <p>
 * ProxyException extends the RuntimeException class, which means it is an unchecked exception.
 * It can be thrown at runtime and does not need to be explicitly declared in the method signature or caught.
 */
public class ProxyException extends RuntimeException {

    /**
     * Constructs a new ProxyException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public ProxyException(String cause) {
        super(cause);
    }

    /**
     * Constructs a new ProxyException with the specified detail message and cause.
     *
     * @param cause     the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     * @param throwable the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that the cause is nonexistent
     *  or unknown.)
     */
    public ProxyException(String cause, Throwable throwable) {
        super(cause, throwable);
    }
}
