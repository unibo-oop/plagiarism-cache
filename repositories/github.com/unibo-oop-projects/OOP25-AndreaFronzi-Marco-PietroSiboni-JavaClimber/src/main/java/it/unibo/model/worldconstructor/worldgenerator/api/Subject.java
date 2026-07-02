package it.unibo.model.worldconstructor.worldgenerator.api;

import it.unibo.model.worldconstructor.data.Difficult;

/**
 * Interface for the Subject in the Observer pattern.
 * Manages observers and notifies them of changes.
 */
public interface Subject {

    /**
     * Registers an observer to receive updates.
     * 
     * @param o the observer to register
     * @return true if the observer was successfully registered, false otherwise.
     */
    boolean registerObserver(Observer o);

    /**
     * Removes an observer from the notification list.
     * 
     * @param o the observer to remove
     * @return true if the observer was successfully removed, false otherwise.
     */
    boolean removeObserver(Observer o);

    /**
     * Notifies all registered observers of a difficulty change.
     * 
     * @param difficult the new difficulty settings
     */
    void notifyObservers(Difficult difficult);
}
