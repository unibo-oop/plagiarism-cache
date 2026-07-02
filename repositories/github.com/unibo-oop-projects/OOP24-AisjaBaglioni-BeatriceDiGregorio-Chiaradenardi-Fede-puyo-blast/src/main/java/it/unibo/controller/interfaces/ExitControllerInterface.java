package it.unibo.controller.interfaces;

/**
 * Interface defining the contract for handling the "Exit" action in the game.
 * This interface provides the method to handle the exit event triggered by the user.
 */
public interface ExitControllerInterface {

    /**
     * Method to handle the "Exit" button click event.
     * This method will define the actions to take when the user clicks on the exit button that is stopping the game and 
     * navigating to the main menu.
     */
    void onExitClicked();
}
