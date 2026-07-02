package it.unibo.monoopoly.model.main.api;

import java.util.Optional;

import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.model.deck.impl.DeckImpl;
import it.unibo.monoopoly.model.deck.impl.DeckWrapper;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.gameboard.api.GameBoard;
import it.unibo.monoopoly.model.gameboard.impl.CellWrapper;
import it.unibo.monoopoly.model.player.impl.PlayerImpl;
import it.unibo.monoopoly.model.player.impl.PlayerWrapper;
import it.unibo.monoopoly.model.state.api.ModelState;

/**
 * Interface representing the turn of a player.
 */
public interface MainModel {
    /**
     * Gets the current {@link ModelState}.
     * 
     * @return the current {@link ModelState}.
     */
    ModelState getState();

    /**
     * Set the {@link ModelState} of the model.
     * 
     * @param state to set.
     */
    void setState(ModelState state);

    /**
     * Gets the {@link GameBoard}.
     * 
     * @return the {@link GameBoard}.
     */
    GameBoard getGameBoard();

    /**
     * Gets the actual event occurring.
     * 
     * @return the actual event occurring
     */
    Optional<Event> getEvent();

    /**
     * Set the actual {@link Event}.
     * 
     * @param selectOperations the new event setted.
     */
    void setEvent(Optional<Event> selectOperations);

    /**
     * Correctly finish a turn and set the {@link ModelState}.
     */
    void nextTurn();

    /**
     * Return the {@link PlayerImpl} wrapped according the pattern Proxy.
     * 
     * @return the {@link PlayerWrapper}.
     */
    PlayerWrapper getPlayerWrapper();

    /**
     * Return the {@link DeckImpl} wrapped according the pattern Proxy.
     * 
     * @return the {@link DeckWrapper}.
     */
    DeckWrapper getDeckWrapper();

    /**
     * Return the actual {@link Cell} wrapped according the pattern Proxy.
     * 
     * @return the {@link CellWrapper}.
     */
    CellWrapper getCellWrapper();

}
