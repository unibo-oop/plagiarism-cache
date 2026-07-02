package it.unibo.monoopoly.model.gameboard.api;

import org.apache.commons.lang3.tuple.Pair;

/**
 * This interface rapresents dices to be used from the player in the game.
 */
public interface Dices {

    /**
     * create a pair of Integer random from 1 to 6.
     */
    void rollDices();

    /**
     * Gets the dices rolled.
     * 
     * @return the Pair of dices rolled
     */
    Pair<Integer, Integer> getDices();

    /**
     * Gets the sum of two dices.
     * 
     * @return the sum of two dices rolled
     */
    int getResult();

}
