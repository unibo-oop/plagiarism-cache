package controller.session;

import java.util.List;

import utility.Driver;
import utility.Pair;

/**
 * Interface for classes that have to deal with the qualifying rank.
 */
public interface QualifyingRank {

    /**
     * Add a player to the qualifying rank using his dice's throws.
     * @param drv the driver to insert in the rank
     * @param diceThrows the number of the dice's throws he did
     */
    void addPlayer(Driver drv, int diceThrows);

    /**
     * Get the final or partial rank of the qualifying.
     * @return the list of driver and how much throws they did.
     */
    List<Pair<Driver, Integer>> getRank();

}
