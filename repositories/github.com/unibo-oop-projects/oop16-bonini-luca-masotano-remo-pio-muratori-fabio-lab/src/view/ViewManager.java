package view;

import controller.event.Event;
import view.state.ViewState;

/**
 * This interface provides usefull methods to manage both the
 * {@link javafx.scene.Scene} graph contents and comunications with
 * {@link controller.Observer}.
 */
public interface ViewManager {
    /**
     * Manage the change of the current state as well as the scene contents; the new
     * state knows how the state stack and the scene graph must be changes.
     * 
     * @param newState
     *            the ViewState object to use as a strategy to modify scene, provide
     *            observers, handle key events
     */
    void pushState(ViewState newState);

    /**
     * Remove the state at the top of the states stack, adapting both the scene
     * contents and the current state references.
     */
    void popState();

    /**
     * Notify the Observable variable that an event occurred.
     * 
     * @param event
     *            the specific ViewEvent event occurred
     */
    void sendViewEvent(Event event);

    /**
     * Used to receive calls from GameView to manage specific events of the game
     * like "game over" or "room change".
     * 
     * @param string
     *            the specific event identifier
     */
    void receiveGameEvent(String string);

    /**
     * Resize the scene by setting the scale property.
     */
    void resizeScene();

    /**
     * Getter of the variable gameRunning.
     * 
     * @return true if there's a game "on hold" false otherwise
     */
    boolean isGameRunning();
}
