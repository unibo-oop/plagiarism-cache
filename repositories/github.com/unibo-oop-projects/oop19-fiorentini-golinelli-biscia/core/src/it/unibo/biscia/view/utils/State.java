package it.unibo.biscia.view.utils;

/**
 * A state.
 *
 * @param <T> The type of state's value.
 */
public interface State<T> {

    /**
     * Sets the current state value to {@code newStateValue}.
     * 
     * @param newStateValue the new value of the State
     */
    void setState(T newStateValue);

    /**
     * The current State value.
     * 
     * @return T The current State value.
     */
    T getCurrentStateValue();

    /**
     * T's runtime class reference.
     * 
     * @return T's runtime class reference.
     */
    Class<?> getType();

}
