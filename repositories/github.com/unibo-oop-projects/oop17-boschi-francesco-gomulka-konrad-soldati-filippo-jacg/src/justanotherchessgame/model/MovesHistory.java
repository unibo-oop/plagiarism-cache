package justanotherchessgame.model;

import java.util.List;

/**
 * Interface used to represent and manage the history of the game moves.
 */
public interface MovesHistory {

    /**
     * Method which represents the color of the next expected move.
     * @return a boolean representing the next expected color.
     */
    boolean nextColor();

    /**
     * Method which adds a move to the log.
     * @param m the move which has to be added to the log.
     */
    void addMove(MoveInfoImpl m);

    /**
     * Method which returns the list of all performed moves in the current log.
     * @return the list of all performed moves in the current log.
     */
    List<MoveInfoImpl> getMoves();

    /**
     * Method which returns the list of all performed moves in the current log until a certain index.
     * @param index is the limit of the history.
     * @return the list of all performed moves in the current log until a certain index.
     */
    List<MoveInfoImpl> getMovesTill(int index);
}
