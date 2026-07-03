package oop.lit.model.actions;

import java.util.Optional;

import oop.lit.util.IllegalInputException;
import oop.lit.util.InputRequest;

/**
 * A utility class for actions.
 */
public final class Actions {
    /**
     * The error message inside the IllegalInputException if the provided request optional is empty.
     */
    public static final String NO_REQUEST_ERR_MSG = "This action needs input";
    /**
     * The default error message for the IllegalInputException thrown when there is no value stored.
     */
    public static final String NO_VALUE_ERR_MSG = "Insert a value for ";

    private Actions() {
    }

    /**
     * Checks the provided input request optional, and returns the stored value.
     * If the provided input request is empty throws an IllegalInputRequest with the message NO_REQUEST_ERR_MSG.
     * If the input request stored value is empty throws an IllegalInputRequest with a default message based on the provided action label.
     * @param request
     *      an optional of an input request.
     * @return
     *      the stored value.
     *
     * @param <T>
     *      the type of the input request.
     *
     * @throws IllegalInputException
     *      if the request is empty or the stored value is empty.
     */
    public static <T> T checkPresentAndGet(final Optional<InputRequest<T>> request) throws IllegalInputException {
        if (!request.isPresent()) {
            throw new IllegalInputException(NO_REQUEST_ERR_MSG);
        }
        return checkPresentAndGet(request, NO_VALUE_ERR_MSG + "\"" + request.get().getLabel() + "\"");
    }

    /**
     * Checks the provided input request optional, and returns the stored value.
     * If the provided input request is empty throws an IllegalInputRequest with the message NO_REQUEST_ERR_MSG.
     * If the input request stored value is empty throws an IllegalInputRequest with the provided message.
     * @param request
     *      an optional of an input request.
     * @param message
     *      the message of the exception if there is no value stored.
     * @return
     *      the stored value.
     *
     * @param <T>
     *      the type of the input request.
     *
     * @throws IllegalInputException
     *      if the request is empty or the stored value is empty.
     */
    public static <T> T checkPresentAndGet(final Optional<InputRequest<T>> request, final String message) throws IllegalInputException {
        final Optional<T> stored = checkAndGet(request);
        if (!stored.isPresent()) {
            throw new IllegalInputException(message);
        }
        return stored.get();
    }

    /**
     * Checks the provided input request optional, and returns the optional stored value.
     * If the provided input request is empty throws an IllegalInputRequest with the message NO_REQUEST_ERR_MSG.
     *
     * @param request
     *      an optional of an input request.
     * @return
     *      the stored value.
     *
     * @param <T>
     *      the type of the input request.
     *
     * @throws IllegalInputException
     *      if the request is empty.
     */
    public static <T> Optional<T> checkAndGet(final Optional<InputRequest<T>> request) throws IllegalInputException {
        if (!request.isPresent()) {
            throw new IllegalInputException(NO_REQUEST_ERR_MSG);
        }
        return request.get().getStoredValue();
    }
}
