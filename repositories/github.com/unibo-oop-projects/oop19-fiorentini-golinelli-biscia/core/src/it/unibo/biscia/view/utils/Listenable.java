package it.unibo.biscia.view.utils;

/**
 * Been able to be listened by a {@link listener}.
 *
 */
public interface Listenable {

    /**
     * Add a listener to this listenable.
     * 
     * @param listener the listener instance.
     */
    void addListener(Listener listener);

    /**
     * Remove a specific instance of a listener from the set of listeners.
     * 
     * @param listener The reference of the listener to be removed.
     */
    void removeListener(Listener listener);

    /**
     * Update all listeners.
     * 
     */
    void update();

}
