package view.utility;

import java.util.Optional;

/**
 * 
 * Class used to create memento objects.
 *
 */

public class Originator {

    private Pair<Pair<Object, Optional<Integer>>, Pair<Integer, Integer>> state;
    
    /**
     * Sets the actual state to the field.
     * @param status The state.
     */
    
    public void setState(final Pair<Pair<Object, Optional<Integer>>, Pair<Integer, Integer>> status) {
        this.state = status;
    }
    
    /**
     * Gives the actual state of the field.
     * @return The state.
     */

    public Pair<Pair<Object, Optional<Integer>>, Pair<Integer, Integer>> getState() {
        return state;
    }
    
    /**
     * Creates a new Memento object with state the actual field.
     * @return The Memento object.
     */

    public Memento saveStateToMemento() {
        return new Memento(state);
    }
    
    /**
     * Save the state of a memento object into the field.
     * @param memento The memento object.
     */

    public void getStateFromMemento(final Memento memento) {
        this.state = memento.getState();
    }
}