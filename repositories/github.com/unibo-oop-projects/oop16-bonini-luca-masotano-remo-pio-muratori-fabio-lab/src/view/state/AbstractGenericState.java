package view.state;

import java.util.List;
import java.util.Stack;

import controller.Observer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.SceneManager;
import view.controller.ControllerFXML;

/**
 * Abstract class containing the common states behavior.
 */
public abstract class AbstractGenericState implements ViewState {

    private final EventHandler<KeyEvent> genericKeyHandler;
    private final ControllerFXML controller;

    /**
     * Constructor of AbstractGenericState class.
     * 
     * @param controller
     *            the controller initialized by subclasses
     */
    protected AbstractGenericState(final ControllerFXML controller) {
        this.controller = controller;
        this.genericKeyHandler = event -> {
            if (event.getCode().equals(KeyCode.ESCAPE) && event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
                this.exitState();
            }
            this.specificKeyHandler(event);
        };
    }

    @Override
    public EventHandler<KeyEvent> getKeyEventHandler() {
        return genericKeyHandler;
    }

    /**
     * Method used to handle specific keys. A state may not have a specific key to
     * handle so implementing this method in {@link AbstractGenericState} reduce
     * duplicate code. If instead a state must handle some specific key this method
     * will be overrided.
     * 
     * @param event
     *            the {@link KeyEvent} to handle
     */
    protected void specificKeyHandler(final KeyEvent event) {
    }

    /**
     * Getter of the variable controller.
     * 
     * @return the initialized controller setted in the constructor
     */
    protected ControllerFXML getController() {
        return this.controller;
    }

    @Override
    public abstract List<Observer> getObservers();

    @Override
    public abstract void modifyScene(SceneManager manager);

    @Override
    public abstract void modifyStateStack(Stack<ViewState> stateStack);

    /**
     * Whenever the ESCAPE key is pressed the current state must handle the exit
     * "request".
     */
    protected abstract void exitState();
}
