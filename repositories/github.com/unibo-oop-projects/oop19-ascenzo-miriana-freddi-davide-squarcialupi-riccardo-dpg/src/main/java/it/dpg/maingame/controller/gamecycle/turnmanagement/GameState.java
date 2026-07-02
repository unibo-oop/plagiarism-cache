package it.dpg.maingame.controller.gamecycle.turnmanagement;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;

/**
 * keeps track of the current state of a turn
 */
public interface GameState {

    /**
     * starts a new turn, it needs to be called at least once before accessing other methods
     */
    void newTurn();

    /**
     * notify if the dice has been thrown or not
     *
     * @throws IllegalStateException if newTurn was never called
     */
    void setDiceThrown(final boolean wasThrown);

    /**
     * @return true if the dice has been thrown, false otherwise
     * @throws IllegalStateException if newTurn was never called
     */
    boolean wasDiceThrown();

    /**
     * notify if a choice has to be done or not
     *
     * @throws IllegalStateException if newTurn was never called
     */
    void setChoice(final boolean isChoosing);

    /**
     * @return true if the player is currently choosing, false otherwise
     * @throws IllegalStateException if newTurn was never called
     */
    boolean isChoosing();

    /**
     * get the last direction choice made by a player/cpu
     *
     * @return Optional.empty if no choice has been made in the turn, or the chosen cell otherwise
     */
    Optional<Pair<Integer, Integer>> getLastDirectionChoice();

    /**
     * save the last direction choice of the player/cpu currently playing the turn
     *
     * @param direction id of the chosen cell
     * @throws IllegalStateException if newTurn was never called
     */
    void setLastDirectionChoice(final Pair<Integer, Integer> direction);

    /**
     * notify if the turn is currently paused or not
     *
     * @throws IllegalStateException if newTurn was never called
     */
    void setTurnPause(final boolean isPaused);

    /**
     * @return true if the turn is paused, false otherwise
     * @throws IllegalStateException if newTurn was never called
     */
    boolean isPaused();
}
