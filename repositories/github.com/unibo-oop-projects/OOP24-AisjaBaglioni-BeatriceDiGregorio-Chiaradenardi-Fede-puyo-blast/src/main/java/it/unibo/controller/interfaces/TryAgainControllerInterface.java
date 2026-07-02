package it.unibo.controller.interfaces;

/**
 * Interface defining the contract for handling the "Try Again" action in the game.
 * This interface provides the method to handle the try again event triggered by the user.
 */
public interface TryAgainControllerInterface {

    /**
     * Method to handle the "Try Again" button click event.
     * This method will define the actions to take when the user clicks on the try again button, that is restarting the game
     * with the previous configuration.
     */
    void handleClick();
}
