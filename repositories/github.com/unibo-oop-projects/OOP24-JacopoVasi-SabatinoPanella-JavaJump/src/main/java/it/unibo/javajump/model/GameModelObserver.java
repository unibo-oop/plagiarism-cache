package it.unibo.javajump.model;

/**
 * The interface that describes a Game model observer, and what it should implement.
 */
public interface GameModelObserver {

    /**
     * Method to call in observers when model updates observers.
     *
     * @param model the GameModel which updates the observer
     */
    void onModelUpdate(GameModel model);
}
