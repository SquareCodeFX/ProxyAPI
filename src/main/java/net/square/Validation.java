package net.square;

import lombok.experimental.UtilityClass;

/**
 * Utility class for performing validation checks.
 */
@UtilityClass
public class Validation {

    /**
     * Checks if the given reference is not null. If the reference is null, a NullPointerException
     * with the given error message is thrown.
     *
     * @param reference    The reference to check for null.
     * @param errorMessage The error message to include in the NullPointerException.
     * @param <T>          The type of the reference.
     * @throws NullPointerException If the reference is null.
     */
    public <T> void checkNotNull(T reference, String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(errorMessage);
        }
    }
}
