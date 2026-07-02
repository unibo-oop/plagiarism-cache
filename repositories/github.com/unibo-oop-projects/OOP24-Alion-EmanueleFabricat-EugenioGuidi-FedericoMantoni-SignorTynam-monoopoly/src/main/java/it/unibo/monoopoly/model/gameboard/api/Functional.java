package it.unibo.monoopoly.model.gameboard.api;

import java.util.Optional;

import it.unibo.monoopoly.common.Message;
/**
 * Represents the cells of the gameboard not buyable.
 */
public interface Functional extends Cell {

    /**
     * Gets the action triggered by the cell.
     * 
     * @return the action triggered by the cell
     */
    Optional<Message> getAction();

    /**
     * Returns whether the cell trigger an action.
     * 
     * @return true if the cell trigger an action, false otherwise
     */
    boolean hasAction();

}
