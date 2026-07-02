package controller;

/**
 * This is a functional interface used by components external to the GameLoop to be notified of events.
 */
public interface GameObserver {

    /**
     * Called once every tick. Tells the Observer that something has happened.
     * @param o The object that has changed.
     */
    void update(Object o);

}
