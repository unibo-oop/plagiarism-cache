package controller;

import java.util.Optional;

import controller.input.CommandType;
import utility.GameModes;

/**
 * Allows the game to run such as starting and stopping the game loop,
 * initializing model, handling inputs and closing the application.
 */
public interface Controller {

    /**
     * Initialize game's model.
     * 
     * @param gameMode
     *            the game mode that must be loaded.
     */
    void initGame(GameModes gameMode);

    /**
     * Start game loop that update model and give to view data.
     */
    void startGame();

    /**
     * Stop the game.
     */
    void stopGame();

    /**
     * 
     * Save the game just ended.
     * 
     * @param name
     *            player's name
     */
    void saveGame(Optional<String> name);

    /**
     * Add the command at the commandList.
     * 
     * @param commandType
     *            the type of the command.
     */
    void notifyCommand(CommandType commandType);

}
