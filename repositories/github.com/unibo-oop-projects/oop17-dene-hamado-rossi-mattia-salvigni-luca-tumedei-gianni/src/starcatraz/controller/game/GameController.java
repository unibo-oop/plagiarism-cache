package starcatraz.controller.game;

import javafx.event.ActionEvent;
import starcatraz.model.Statistics;
import starcatraz.model.game.Game;

/**
 * Game Controller.
 */
public interface GameController {

    /**
     * @return instance of Game
     */
    Game getGame();

    /**
     * @param statistics to use inside the controller
     */
    void setStatistics(Statistics statistics);

    /**
     * Increment the number of robots defeated.
     */
    void incrementDefeatedRobots(); 

    /**
     * Set game pause time.
     */
    void setPauseTime(final long pauseTime);

    /**
     * @return Milliseconds passed with the game paused
     */
    long getPauseTime();

    /**
     * Go back to the main menu.
     * @param event
     */
    void backButtonClick(ActionEvent event);

    /**
     * Restart the match.
     * @param event
     */
    void restartButtonClick(ActionEvent event);

    /**
     * Pause the game.
     * @param event
     */
    void pauseButtonClick(ActionEvent event);

    /**
     * Called to show a popupWindow.
     * @param fxmlPath: fxml file to load inside the popup window
     */
    void showPopupStage(String fxmlPath);

    /**
     * @return the controller of the currently loaded popup stage
     */
    GamePopupController getPopupStageController();

    /**
     * Called by the popup windows to close themselves.
     */
    void hidePopupStage();
}
