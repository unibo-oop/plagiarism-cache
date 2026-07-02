package it.unibo.the100dayswar.commons.patterns;

/**
 * Interface for a class that can notify its observers.
 * 
 * @param <T> the type of the source of a possible event
 */
public interface Observable<T> {
    /**
     * Add an observer to the list of observers.
     * 
     * @param observer the observer to be added
     */
    void attach(Observer<T> observer);
    /**
     * Remove an observer from the list of observers.
     * 
     * @param observer the observer to be removed
     */
    void detach(Observer<T> observer);
}
