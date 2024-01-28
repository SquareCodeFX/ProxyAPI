package net.square.exceptions.impl;

import net.square.exceptions.ProxyException;

public class AddressDataFetchingException extends ProxyException {

    public AddressDataFetchingException(String message, Throwable cause) {
        super(message, cause);
    }
}