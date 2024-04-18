package net.square.exceptions.impl;

import net.square.exceptions.ProxyException;

/**
 * The ProxyCheckBlockingException class represents an exception that occurs when the response from the proxy check API indicates blocking.
 * It extends the ProxyException class, which is a custom exception class for handling proxy-related errors.
 * <p>
 * ProxyCheckBlockingException is a checked exception, meaning that it must be declared in the method signature or caught.
 * It can be thrown when the response from the proxy check API indicates blocking.
 */
public class ProxyCheckBlockingException extends ProxyException {

    /**
     * ProxyCheckBlockingException is an exception that occurs when the response from the proxy check API indicates blocking.
     * It extends the ProxyException class, which is a custom exception class for handling proxy-related errors.
     * <p>
     * ProxyCheckBlockingException is a checked exception, meaning that it must be declared in the method signature or caught.
     * It can be thrown when the response from the proxy check API indicates blocking.
     */
    public ProxyCheckBlockingException(String message) {
        super(message);
    }
}
