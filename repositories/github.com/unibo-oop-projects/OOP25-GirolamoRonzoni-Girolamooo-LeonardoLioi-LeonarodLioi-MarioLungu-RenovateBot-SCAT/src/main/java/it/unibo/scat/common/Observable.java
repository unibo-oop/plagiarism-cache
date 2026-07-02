package it.unibo.scat.common;

/**
 * Interface that defines the subject in the Observer pattern.
 */
public interface Observable {

    /**
     * Sets the observer.
     * 
     * @param o the instance to bind to this subject.
     */
    void setObserver(Observer o);

    /**
     * Notifies the observer that a state change happened.
     */
    void notifyObserver();
}
