package oop.lit.util;

import java.util.Optional;

/**
 * A request for an input by the user.
 *
 * @param <T> the type of input needed
 */
public interface InputRequest<T> {
    /**
     * @return  a description of what the input will be used for
     */
    String getLabel();

    /**
     * stores the value inserted by the user.
     *
     * @throws IllegalInputException if the player input is invalid.
     */
    void storeValue() throws IllegalInputException;

    /**
     * Get the stored value; this method should only be used by the object needing the input.
     * @return the stored value.
     */
    Optional<T> getStoredValue();
}
