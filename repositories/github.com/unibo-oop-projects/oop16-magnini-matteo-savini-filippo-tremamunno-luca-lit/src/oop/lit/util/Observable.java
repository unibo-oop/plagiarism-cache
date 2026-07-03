package oop.lit.util;

/**
 * An object that notifies all attached observers when this changes.
 */
public interface Observable {
    /**
     * Adds an observer.
     *
     * @param o
     *      an object that will be notified when this changes.
     */
    void attach(Observer o);

    /**
     * Removes an observer.
     *
     * @param o
     *      an object that does not want to be notified anymore.
     */
    void detach(Observer o);
}
