package it.unibo.biscia.events;

import it.unibo.biscia.core.Direction;
import it.unibo.biscia.core.Player;

/**
 * listener for player commands.
 *
 */
public interface ActionObserver {

    /**
     * request for moving snake.
     * 
     * @param player    player to want move
     * @param direction direction of movement
     */
    void move(Player player, Direction direction);

    /**
     * request of game suspension o restart.
     */
    void pauseAndResume();

    /**
     * request of game end.
     */
    void end();
}
