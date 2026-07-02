package it.unibo.jurassiko.core.api;

import java.util.Map;
import java.util.Optional;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.api.Player.GameColor;
import it.unibo.jurassiko.model.territory.api.Territory;

/**
 * Interface used to check if any player has completed their objective and to
 * set the winning player.
 */
public interface WinCondition {

    /**
     * Checks if some player has completed their objective and if that is the case
     * that player is returned.
     *
     * @param territoriesMap the map representing the owner of all territories.
     * @param player         the player to be checked.
     * @param objective      the objective of the player to check.
     * @return an Optional containing the winning player if there is one, or an
     *         empty Optional otherwise.
     */
    Optional<Player> getWinner(Map<Territory, Pair<GameColor, Integer>> territoriesMap,
            Player player,
            Objective objective);

}
