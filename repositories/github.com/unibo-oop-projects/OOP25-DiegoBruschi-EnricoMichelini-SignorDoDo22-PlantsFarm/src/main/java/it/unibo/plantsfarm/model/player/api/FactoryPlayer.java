package it.unibo.plantsfarm.model.player.api;

import it.unibo.plantsfarm.model.player.PlayersTypes;

/**
 * Factory interface used to create different types of Player.
 * Each player type represents a different gameplay style,
 * such as a faster or slower farmer.
 */
@FunctionalInterface
public interface FactoryPlayer {

    /**
     * Creates a new Player based on the requested type.
     *
     * @param request the type of player to create
     *
     * @return the created Player
     */
    Player createPlayer(PlayersTypes request);
}
