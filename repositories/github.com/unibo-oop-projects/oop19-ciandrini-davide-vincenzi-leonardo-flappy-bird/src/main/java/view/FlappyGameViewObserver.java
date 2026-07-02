package view;

/**
 * FlappyGameViewObserver implemented by FlappyBirdControllerImpl allows view-controller communication.
 */
public interface FlappyGameViewObserver {

    /**
     * Add to view the bird and the first pair of tube.
     */
    void startGame();

    /**
     * View intercepts user input and inform controller about that.
     */
    void pressSpace();

}
