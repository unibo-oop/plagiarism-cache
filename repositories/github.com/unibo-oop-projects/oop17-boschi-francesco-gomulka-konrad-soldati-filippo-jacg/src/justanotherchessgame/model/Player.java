package justanotherchessgame.model;

import java.util.List;

import justanotherchessgame.util.GameResult;
import justanotherchessgame.util.Point;

/**
 * Abstract Class representing a player.
 */
public abstract class Player {

    /**
     * Is the color of the player.
     */
    protected boolean color;

    /**
     * Is the game of the player.
     */
    protected GameModel game;
    /**
     * Player constructor.
     * @param color is the player color (white or black).
     */
    protected Player(final boolean color) {
        this.color = color;
    }

    /**
     * Setter of the players game.
     * @param gameModel is the current game.
     */
    public void setGame(final GameModel gameModel) {
        game = gameModel;
    }
    /**
     * Getter of the players color.
     * @return a boolean representing the players color.
     */
    public boolean getColor() {
        return this.color;
    }
    /**
     * Method which notifies a given move to the controller.
     * @param m is the move which has to be notified to the controller.
     */
    public abstract void notifyMove(MoveInfoImpl m);
    /**
     * Method which request a certain move to the game.
     * @param m is the move which has to be notified to the game.
     */
    public abstract void requestMove(MoveInfoImpl m);
    /**
     * Method which returns a list of possible moves given a certain point.
     * @param p is the point from which the moves have to start.
     * @return the list of possible moves.
     */
    public abstract List<MoveInfoImpl> possibleMoves(Point p);
    /**
     * Method which returns a list of all performed moves during the current game.
     * @return a list of all performed moves during the current game.
     */
    public abstract List<MoveInfoImpl> getMoveHistory();

    /**
     * Method to notify a player about the ending result of the game.
     * @param result is the result of the game..
     */
    public abstract void notifyResult(GameResult result);
}
