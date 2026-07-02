package it.unibo.isaccoop.controller.input;
/**
 * Interface for the action controller.
 */
public interface ActionController {

    /**
     * Get if ESC button is pressed.
     * @return true if Esc button has been pressed
     */
    boolean isEsc();

    /**
     * Get if NextRoom button has been pressed.
     * @return true if NextRoom button has been pressed
     */
    boolean isNextRoom();

    /**
     * Get if PrevRoom button has been pressed.
     * @return true if PrevRoom button has been pressed
     */
    boolean isPrevRoom();
}
