package javagotchi.controller.minigame;

import javagotchi.controller.home.HomeController;
import javagotchi.controller.minigame.file.SavedData;
import javagotchi.model.minigame.MiniModel;
import javagotchi.view.minigame.MiniView;

/**
 * @author marica
 */
public interface ControllerMiniGame {

    /**
     * Loads a new game.
     */
    void newGame();

    /**
     * Activates the game. Starts timer and sets the game state to START.
     */
    void startGame();

    /**
     * Plays the game.
     */
    void playGame();

    /**
     * Pauses the game, actually pauses the timer.
     */
    void pauseGame();

    /**
     * Resumes the game from the pause.
     */
    void resumeGame();

    /**
     * The game is over if timer is over or the player press white button.
     */
    void endGame();

    /**
     * Resets the game.
     */
    void resetGame();

    /**
     * Saves the game.
     */
    void saveGame();

    /**
     * Continues the saved game.
     */
    void continueGame();

    /**
     * Goes back to main menu, without saving the game.
     */
    void backToMenu();

    /**
     * Closes the Mini Game and reactives the timers of javagotchi.
     */
    void exit();

    /**
     * Gets Model instance.
     * 
     * @return Model istance
     */
    MiniModel getModel();

    /**
     * Gets View instance.
     * 
     * @return View istance
     */
    MiniView getView();

    /**
     * Gets SavedData instance.
     * 
     * @return saved data
     */
    SavedData getSavedData();

    /**
     * Sets Home Controller.
     * 
     * @param hc
     *            Home Controller instance
     */
    void setHomeController(HomeController hc);

    /**
     * Gets Home Controller instance.
     * 
     * @return Home Controller instance
     */
    HomeController getHomeController();

}
