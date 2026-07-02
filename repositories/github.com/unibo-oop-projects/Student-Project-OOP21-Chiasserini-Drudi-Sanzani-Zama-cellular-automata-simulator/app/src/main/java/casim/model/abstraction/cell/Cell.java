package casim.model.abstraction.cell;

/**
 * A {@link Cell} of the Grid.
 * 
 * @param <T> the enumeration which contains the finite states of the Automaton's {@link Cell}.
 */
public interface Cell<T> {

    /** 
     * Return the current state of the {@link Cell}.
     * 
     * @return the current state of the {@link Cell}.
     */
    T getState();

}
