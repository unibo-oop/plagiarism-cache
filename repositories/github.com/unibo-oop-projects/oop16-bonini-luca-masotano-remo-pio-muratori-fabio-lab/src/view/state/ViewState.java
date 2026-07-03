package view.state;

import java.util.List;
import java.util.Stack;

import controller.Observer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import view.SceneManager;

/**
 * State pattern interface; provides methods needed by the principal class
 * {@link ViewManagerImpl} to work. There are differen types of states based on how they
 * modify the Scene and the {@link ViewManagerImpl} inner states stack; moreover, a
 * state must provides it's observers and key event handler.
 */
public interface ViewState {

    /**
     * Provide the specific implementation of what to do when a key event is
     * detected.
     * 
     * @return a specific key event handler
     */
    EventHandler<KeyEvent> getKeyEventHandler();

    /**
     * Modify the current javafx scene graph to display this state contents
     * properly.
     * 
     * @param manager
     *            the object which provides methods to modify the scene graph
     */
    void modifyScene(SceneManager manager);

    /**
     * Modify the current state stack collection to properly set the stack head;
     * this can be done in two different ways: by cleaning the stack and setting
     * this state as main and only state or by adding itself to the stack keeping
     * all the previous states.
     * 
     * @param stateStack
     *            the stack containing all the recently used states
     */
    void modifyStateStack(Stack<ViewState> stateStack);

    /**
     * Provides a list of initialized observers used by the current state.
     * 
     * @return a collection of observers
     */
    List<Observer> getObservers();
}
