package breakout.controller;

import java.util.List;

interface InputHandler {

    /**
     * Resolve an input with the specific action.
     * 
     * @param input
     */
    void resolveInput(final Input input);

    /**
     * Resolve all the inputs of a list.
     * 
     * @param inputList
     *            all the inputs to be resolved
     */
    default void resolveAll(final List<Input> inputList) {
        inputList.forEach(input -> this.resolveInput(input));
    }

}
