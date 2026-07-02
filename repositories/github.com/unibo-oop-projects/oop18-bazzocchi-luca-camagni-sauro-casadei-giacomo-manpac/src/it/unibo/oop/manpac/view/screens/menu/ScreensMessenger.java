package it.unibo.oop.manpac.view.screens.menu;

import it.unibo.oop.manpac.controller.ControllerObserver;
import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Entity;

/**
 * This interface is implemented in the screens and is used by the controller to
 * communicate with it by calling public methods.
 */
public interface ScreensMessenger {

    /**
     * To attach a controller as an observer.
     * 
     * @param controller {@link ControllerObserver} of the game
     */
    void startControllerObserving(ControllerObserver controller);

    /**
     * To detach the controller.
     */
    void stopControllerObserving();

    /**
     * Interface for the settings view; is used by the controller to communicate
     * with it.
     *
     */
    interface TheSettings extends ScreensMessenger {

        /**
         * Send the high score to the view.
         * 
         * @param highScore The high score of the player
         */
        void sendHighScore(int highScore);

    }

    /**
     * Interface for the game view; is used by the controller to communicate with
     * it.
     *
     */
    interface TheGame extends ScreensMessenger {

        /**
         * Send the current and high score to the view.
         * 
         * @param currentScore The current score of the player
         * @param highScore    The high score of the player
         */
        void sendScore(int currentScore, int highScore);

        /**
         * Send the current lives of Pac-Man.
         * @param lives of the Pac-Man
         */
        void sendPacManLives(int lives);

        /**
         * Sends an {@link Action} that Pac-Man must perform.
         * 
         * @param action The {@link Action} to be sent
         */
        void sendPacManAction(Action action);

        /**
         * Sends ad direction ({@link Action}) that Pac-Man must take.
         * 
         * @param directionAction The direction ({@link Action}) that Pac-Man must take. 
         */
        void sendPacManDirectionWithAction(Action directionAction);

        /**
         * Sends an {@link Action} that phantom must perform.
         * 
         * @param action      The {@link Action} to be sent
         * @param phantomName The name ({@link Entity}) of the ghost who must perform
         *                    the action
         */
        void sendPhantomAction(Entity phantomName, Action action);

    }
}
