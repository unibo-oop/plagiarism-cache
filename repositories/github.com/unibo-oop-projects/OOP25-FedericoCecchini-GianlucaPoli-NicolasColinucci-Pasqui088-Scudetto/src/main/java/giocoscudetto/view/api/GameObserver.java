package giocoscudetto.view.api;

/**
 * This interface represents an observer of the game, it has a method to update the state of the view when the game changes.
 */
@FunctionalInterface
public interface GameObserver {

    /**
     * this method apdate the state of a view in my implemetation.
     */
    void updateState();

}
