package net.square.exceptions;

public class ProxyException extends RuntimeException {

    public ProxyException(String cause) {
        super(cause);
    }

    public ProxyException(String cause, Throwable throwable) {
        super(cause, throwable);
    }
}
