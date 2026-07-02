package controller;

import javafx.util.Pair;
import model.players.Player;

/**
 *
 * Piero Sanchi. Interface of the Controller.
 *
 */
public interface Controller {

    /**
     *
     * @param player
     *            the player to add
     */
    void addPlayer(Player player);

    /**
     *
     * @return true if audio is active.
     */
    boolean isAudioOn();

    /**
     *
     * @return the number of heroes a player must bring to the temple to win.
     */
    int getCharactersForWin();

    /**
     *
     * @return the number of steps that the minotaurus makes when invoked.
     */
    int getMinotaurusSteps();

    /**
     *
     * @return the high score rank string.
     */
    String getRanking();

    /**
     *
     * @return the number of the selected maze.
     */
    int getSelectedMaze();

    /**
     *
     * @return true if the game is in hedge jumping mode, false otherwise.
     */
    boolean isHedgeJumpingMode();

    /**
     *
     * @return true if the minotaurus is in hedge jump mode, false otherwise.
     */
    boolean isMinotaurusHedgeJumpingMode();

    /**
     *
     * @return true if it's not the last step before the turn ends, false otherwise.
     */
    boolean isNotLastStep();

    /**
     * This method opens the board view.
     */
    void openBoardView();

    /**
     * This method opens the demo board view.
     */
    void openDemoBoardView();

    /**
     * This method reopens the board view when it has been closed.
     */
    void reopenBoardView();

    /**
     * This method reset the state of a game.
     */
    void reset();

    /**
     * Method to roll the dice.
     */
    void rollDice();

    /**
     *
     * @return true if there is a saved game present.
     */
    boolean savedGamePresent();

    /**
     * Method to save the state of a game.
     */
    void saveGame();

    /**
     *
     * @param coord
     *            the coordinate of the figure to select.
     */
    void select(Pair<Integer, Integer> coord);

    /**
     *
     * @param value
     *            the number of heroes at the temple to win.
     */
    void setCharactersForWin(int value);

    /**
     *
     * @param isHedgeJumpingMode
     *            the modality of the game, true if hedgejumping mode.
     */
    void setEdgeJumpingMode(boolean isHedgeJumpingMode);

    /**
     *
     * @param maze
     *            the number of the maze to set for the game.
     */
    void setMaze(int maze);

    /**
     *
     * @param isMinotaurusHedgeJumpingMode
     *            the modality of the minotaurus, true if hedgejumping mode.
     */
    void setMinotaurusEdgeJumpingMode(boolean isMinotaurusHedgeJumpingMode);

    /**
     *
     * @param minotaurusSteps
     *            the standard steps that the minotaurus will make.
     */
    void setMinotaurusSteps(int minotaurusSteps);

    /**
     *
     * @param volume
     *            the volume to set.
     */
    void setVolume(int volume);

    /**
     * Starts the keyboard listener for directional keys, r key and enter, for the
     * demo of the application.
     */
    void startDemoKeyboardListener();

    /**
     * Starts the keyboard listener for directional keys, r key and enter.
     */
    void startKeyboardListener();

    /**
     * Stops the audio in WinDialog.
     */
    void stopWin();

    /**
     * Method for going back of one move.
     */
    void undo();

    /**
     * Updates the position of each element on the BoardView.
     */
    void updateElements();

}
