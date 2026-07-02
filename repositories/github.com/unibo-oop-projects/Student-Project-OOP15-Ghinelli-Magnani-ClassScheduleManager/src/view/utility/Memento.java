package view.utility;

import java.util.Optional;

/**
 * 
 * Class which determine the nature of the memento object.
 *
 */

public class Memento {
    
    private final Pair<Pair<Object, Optional<Integer>>, Pair<Integer, Integer>> state;
    
    /**
     * Constructor of the class.
     * @param status A determined state saved by the object memento.
     */
    
    public Memento(final Pair<Pair<Object, Optional<Integer>>, Pair<Integer, Integer>> status) {
        this.state = status;
    }
    
    /**
     * Method which gives the state of the memento.
     * @return The state, in this case is a Pair<Pair<Object, Optional<Integer>>, Pair <Integer, Integer>>.
     */
    
    public Pair<Pair<Object, Optional<Integer>>, Pair<Integer, Integer>> getState() {
        return this.state;
    }
}
