package controller;

import java.util.List;

import controller.input.InputController;
import core.GameStatus;
import enums.GameMode;
import enums.GameScene;
import enums.KeyInput;
import enums.Stage;
import model.entities.GameEntity;

/**
 * Interface for the controller of the game.
 */
public interface Controller {

    /**
     * Getter method for my highest score.
     * 
     * @return the integer for my highest score.
     */
    int getMyHiScore();

    /**
     * Getter method for the highest score of the game.
     * 
     * @return the highest score of the game.
     */
    int getHiScore();

    /**
     * Getter method for the selected mode of the game.
     * 
     * @return the selected mode of the game.
     */
    GameMode getGameMode();

    /**
     * Getter method for the current stage of the game.
     * 
     * @return the current stage of the game.
     */
    Stage getCurrentStage();

    /**
     * Getter method for the integer of the current stage of the game.
     * 
     * @return the integer of the current stage of the game.
     */
    int getCurrentStageNumber();

    /**
     * Getter method for the game current status.
     * 
     * @return the current game status.
     */
    GameStatus getGameStatus();

    /**
     * Getter method for the input controllers of the players.
     * 
     * @return the input controllers of the players.
     */
    List<InputController> getInputControllers();

    /**
     * Getter method for the maximum stage possible to play.
     * 
     * @return the maximum stage possible to play.
     */
    Stage getMaxStage();

    /**
     * Getter method for the minimum stage possible to play.
     * 
     * @return the minimum stage possible to play.
     */
    Stage getMinStage();

    /**
     * Method that initialize the controller.
     */
    void init();

    /**
     * Method that returns true if the game is running, false otherwise.
     * 
     * @return true if the game is running, false otherwise.
     */
    boolean isGameRunning();

    /**
     * Method that notifies to the controller when a key is pressed from the GUI.
     * 
     * @param keyPressed the key pressed.
     */
    void notifyKeyPressedEvent(KeyInput keyPressed);

    /**
     * Method that notifies to the controller when a key is released from the GUI.
     * 
     * @param keyReleased the key released.
     */
    void notifyKeyReleasedEvent(KeyInput keyReleased);

    /**
     * Method that notifies to the controller a defeat in the game.
     */
    void notifyLose();

    /**
     * Method that notifies to the controller a win in the game.
     */
    void notifyWin();

    /**
     * Method that render the current scene.
     * 
     * @param gameEntities list of game entities to render.
     */
    void renderScene(List<GameEntity> gameEntities);

    /**
     * Setter method for my highest score.
     * 
     * @param myHiScore the integer for my highest score.
     */
    void setMyHiScore(int myHiScore);

    /**
     * Setter method for the highest score of the game.
     * 
     * @param hiScore the highest score of the game.
     */
    void setHiScore(int hiScore);

    /**
     * Setter method for the mode of the game.
     * 
     * @param mode the mode of the game to be set.
     */
    void setGameMode(GameMode mode);

    /**
     * Setter method for the scene of the game.
     * 
     * @param scene the scene of the game to be set.
     */
    void setGameScene(GameScene scene);

    /**
     * Setter method for the game current status.
     * 
     * @param gameStatus the current game status.
     */
    void setGameStatus(GameStatus gameStatus);

    /**
     * Set the next stage if exist.
     */
    void setNextStage();

    /**
     * Set the previous stage if exist.
     */
    void setPreviousStage();

    /**
     * Setter method for the level of the game.
     * 
     * @param stage the level of the game to be set.
     */
    void setStage(Stage stage);

    /**
     * Method that start the game.
     */
    void startGame();

    /**
     * Method that stop the game.
     */
    void stopGame();

}
