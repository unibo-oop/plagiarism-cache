package view;

/**
 * This interface allows the communication between view and controller.
 */
public interface SuperMarioRunGameViewObserver {

    /**
     * This function intercept user input and call the controller.
     */
    void pressSpace();

    /**
     * This function has to add to the view Mario, Floor and Obstacle.
     */
    void newGame();
}
