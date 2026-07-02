package it.unibo.the100dayswar.model.statistic.api;

import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;

import java.io.Serializable;
import java.util.List;

/**
 * Interface that models the game statistics.
 */
public interface GameStatistics extends Serializable {

    /**
     * Update all the statistics.
     */
    void updateAllStatistics();

    /**
     * Get the number of soldiers for each player, sorted by value.
     * @return a Pair of players and their soldier counts.
     */
    Pair<List<Player>, List<Integer>> getSoldiers();

    /**
     * Get the number of towers for each player, sorted by value.
     * @return a Pair of players and their tower counts.
     */
    Pair<List<Player>, List<Integer>> getTowers();

    /**
     * Get the percentage of cells owned by each player, sorted by value.
     * @return a Pair of players and their cell percentages.
     */
    Pair<List<Player>, List<Double>> getCellsPercentage();

    /**
     * Get the balances for each player, sorted by value.
     * @return a Pair of players and their balances.
     */
    Pair<List<Player>, List<Integer>> getBalances();
}
