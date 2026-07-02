package it.unibo.biscia.view.utils;

/**
 * A listener that should be updated when {@link Listener#stateChanged()} is
 * called.
 * 
 * @see Listenable
 * @see StateListenable
 *
 */
public interface Listener {

    /**
     * This method is called when the Listenable calls {@link Listenable#update()}
     * and it should be implemented Consequently.
     * 
     */
    void stateChanged();

}
