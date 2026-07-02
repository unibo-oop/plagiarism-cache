package ballblast.model.inputs;

import java.util.List;

import ballblast.model.components.InputComponent;

/**
 * Manages inputs and redirects them to the right Player thanks {@link InputComponent}.
 */
public interface InputManager {

    /**
     * Adds a inputHandler for a new Player.
     * 
     * @param tag            the tag identifies a specific Player.
     * @param inputComponent the {@link InputComponent} of a specific
     *                       Player.
     */
    void addInputHandler(PlayerTag tag, InputComponent inputComponent);

    /**
     * Removes a inputHandler associated with a specific {@link PlayerTag}.
     * 
     * @param tag the tag which identifies the inputhandler to be removed.
     */
    void removeInputHandler(PlayerTag tag);

    /**
     * Translates received inputs into commands and sends them to the right
     * {@link InputComponent}.
     * 
     * @param tag    the tag which identifes the right Player.
     * @param inputs the inputs to be translated.
     */
    void processInputs(PlayerTag tag, List<InputType> inputs);

    /**
     * All possible Players.
     */
    enum PlayerTag {
        /**
         * First.
         */
        FIRST,
        /**
         * Second.
         */
        SECOND;
    }
}
