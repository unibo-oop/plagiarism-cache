package oop.lit.view;

import java.util.List;

import oop.lit.util.InputRequest;
import oop.lit.util.InputRequestsFactory;

/**
 * A class used to ask the user for something, or to display something to him.
 */
public interface ViewRequests {

    /**
     * @return
     *      this view input request factory
     */
    InputRequestsFactory getIRFactory();

    /**
     * Asks the user to provide input, for the specified requests.
     * @param requests
     *      a list of input request.
     * @return
     *      if the user confirmed the input, and did not cancel.
     * @throws IllegalArgumentException
     *      if the provided input request are not valid for this view (they should be created using this view InputRequestFactory)
     */
    boolean askInput(List<InputRequest<?>> requests);

    /**
     * Displays an error message.
     * @param message
     *      the message to be displayed.
     */
    void displayError(String message);

    /**
     * Displays a message.
     * @param message
     *      the message to be displayed.
     */
    void displayMessage(String message);
}