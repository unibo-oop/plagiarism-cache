package casim.model.abstraction.cell;

/**
 * An abstract implementation of a {@link Cell}.
 * 
 * @param <T> the enumeration which contains the finite states of the Automaton's {@link Cell}.
 */
public abstract class AbstractCell<T> implements Cell<T> {

    private T state;

    /**
     * Construct a new {@link AbstractCell}.
     * 
     * @param state the current state of the {@link Cell}.
     */
    public AbstractCell(final T state) {
        this.state = state;
    }

    @Override
    public T getState() {
        return this.state;
    }

    /**
     * Sets the state of the cell.
     * @param state the state to be set in the {@link Cell}.
     */
    protected void setState(final T state) {
        this.state = state;
    }
}
