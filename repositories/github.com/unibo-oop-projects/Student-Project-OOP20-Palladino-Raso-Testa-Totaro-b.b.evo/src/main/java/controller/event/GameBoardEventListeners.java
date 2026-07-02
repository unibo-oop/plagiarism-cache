package controller.event;

/**
 * 
 * Interface for applying the pattern.
 */
public interface GameBoardEventListeners {
    /**
     * Adds events to the queue that will be resolved with each iteration of gameLoop.
     * @param e event generated
     */
    void eventListener(Event e);
}
