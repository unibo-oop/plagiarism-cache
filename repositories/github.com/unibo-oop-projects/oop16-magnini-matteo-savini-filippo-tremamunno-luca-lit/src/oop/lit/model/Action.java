package oop.lit.model;

import java.util.List;

import oop.lit.util.IllegalInputException;
import oop.lit.util.InputRequest;
import oop.lit.util.InputRequestsFactory;

/**
 *  An action that can be performed by an element.
 */
public interface Action {
    /**
     * @return if the action can be performed.
     */
    boolean canBePerformed();

    /**
     * @return a brief description of the action.
     */
    String getLabel();

    /**
     * Get the input needed by this action to be performed.
     * Different calls of this method may provide different InputRequests. Only the latest InputRequests should be used.
     * @param irFactory
     *      the inputRequestFactory of the used view.
     * @return the list of input needed.
     *
     * @throws IllegalStateException
     *      if the action can't be performed now.
     */
    List<InputRequest<?>> getRequests(InputRequestsFactory irFactory);

    /**
     * Perform this action.
     * 
     * @throws IllegalInputException
     *      if the input is invalid.
     * @throws IllegalStateException
     *      if the action can't be performed now.
     */
    void perform() throws IllegalInputException;
}
