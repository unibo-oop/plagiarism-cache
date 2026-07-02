package it.unibo.oop.manpac.controller;

import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.view.screens.menu.ScreensMessenger;

/**
 * Observer for controller; these methods will be notified from the view.
 */
public interface ControllerObserver {

    /**
     * Default error message that can be used if the controller does not initialized on screens.
     */
    String ERROR_INITIALIZE = "The controller has not been initialized!";

    /**
     * Notifies the observer that the observable has changed and that it must
     * connect to the new observable passed by parameter.
     * 
     * @param view The new observable
     */
    void setCurrentObservable(ScreensMessenger view);

    // METHODS FOR START GAME
    /**
     * It allows to start a new game keeping the current score.
     */
    void requestContinueGame();

    /**
     * It allows to reset and start a new game.
     */
    void requestResetGame();

    /**
     * It allows to set the number pills in the game.
     * 
     * @param number Number of the pills in the game.
     */
    void requestSetNumberPills(int number);
    // ------------------------------------------------

    //METHOD FOR MANAGE THE SCORE
    /**
     * It allows to reset the high and current score.
     */
    void requestResetBothScore();

    /**
     * It allows to reset only current score.
     */
    void requestResetCurrentScore();

    /**
     * It allows to save on file the high score.
     */
    void requestSaveHighScore();
    // ------------------------------------------------

    /**
     * Encapsulates the methods that can be called from the settings view.
     */
    interface OnlySettings extends ControllerObserver {
        /**
         * It allows to get the high score.
         */
        void requestHighScore();
    }

    /**
     * Encapsulates the methods that can be called from the game view.
     */
    interface OnlyGame extends ControllerObserver {

        // FOR STATISTICS----------------------------------
        /**
         * It allows to get the high and current score.
         */
        void requestBothScore();

        /**
         * It allows to get the Pac-Man's lives.
         */
        void requestPacManLives();
        // ------------------------------------------------

        // FOR THE PROGRESS OF THE GAME--------------------
        /**
         * It allows to communicate the new direction of the Pac-Man.
         * 
         * @param newDirections The new directions of Pac-Man
         */
        void pacmanInputDirection(Directions newDirections);

        /**
         * To request what to do when the Pac-Man collides with an entity.
         * 
         * @param entity The entity with which Pac-Man it clashes
         */
        void pacmanIsCollidingToEntity(Entity entity);

        /**
         * To request what to do when a phantom collides with an entity.
         * 
         * @param phantomtName The name of the phantom
         * @param entity       The entity with which ghost it clashes
         */
        void phantomIsCollidingToEntity(Entity phantomtName, Entity entity);

        /**
         * To signal that the time of the power mode effect has ended.
         */
        void stopTimerPower();
        // ------------------------------------------------
    }
}
