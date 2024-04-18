package net.square.exceptions.impl;

import net.square.exceptions.ProxyException;

/**
 * The AddressDataFetchingException class represents an exception that occurs when fetching address data.
 * It extends the ProxyException class, which is a custom exception class for handling proxy-related errors.
 * <p>
 * AddressDataFetchingException is a checked exception, meaning that it must be declared in the method signature or caught.
 * It can be thrown when an error occurs during the fetching of address data.
 */
public class AddressDataFetchingException extends ProxyException {

    /**
     * AddressDataFetchingException is a custom exception class that extends ProxyException.
     * It is used to handle errors that occur during the fetching of address data.
     */
    public AddressDataFetchingException(String message, Throwable cause) {
        super(message, cause);
    }
}