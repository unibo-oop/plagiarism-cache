package controller.gameSwitcher;

import java.io.IOException;

import controller.inputController.InputController;
import controller.ranking.Ranking;
import javafx.stage.Stage;

/**
 * 
 */
public interface SceneController {

    /**
     * Switch to Start Menu.
     *
     * @throws IOException
     */
    void switchToMainMenu() throws IOException;

    /**
     * Switch to Game.
     *
     * @param name
     * @throws IOException
     */
    void switchToGame(String name) throws IOException;

    /**
     * Switch to Score Menu.
     *
     * @throws IOException
     */
    void switchToScores() throws IOException;

    /**
     * Switch to Name Menu.
     *
     * @throws IOException
     */
    void switchToNickname() throws IOException;

    /**
     * Switch to End Game Menu.
     *
     * @param scores
     * @throws IOException
     */
    void switchToEndMenu(int scores) throws IOException;

    /**
     * Switch to Controls Menu.
     *
     * @throws IOException
     */
    void switchToControls() throws IOException;

    /**
     * Quit from the game.
     */
    void quit();

    /**
     * @return Stage reference.
     */
    Stage getStage();

    /**
     * @return Ranking reference.
     */
    Ranking getRanking();

    /**
     * @return input controller reference.
     */
    InputController getInputController();
}
