package it.unibo.model.camera.api;

/**
 * Interface defining the Subject in the Observer pattern for altitude tracking.
 */
public interface AltitudeSubject {

    /**
     * Register a new observer to the list.
     * 
     * @param observer the observer to add
     */
    void addObserver(AltitudeObserver observer);

    /**
     * Removes an observer from the list.
     * 
     * @param observer the observer to remove
     */
    void removeObserver(AltitudeObserver observer);

    /**
     * Notifies all registered observers that the altitude has changed.
     * 
     * @param delta the vertical displacement to comminicate
     */
    void notifyObserver(double delta);
}
