package javawulf.model.enemy;

import javawulf.model.Entity;
import javawulf.model.map.Map;
import javawulf.model.player.Player;

/**
 * Enemies are entities that can move and attack the player.
 */
public interface Enemy extends Entity {

    /**
     * Moves the enemy.
     * 
     * @param p the player
     * @param m the map
     */
    void move(Player p, Map m);

    /**
     * When the enemy takes a hit from the player.
     * 
     * @param p the player
     */
    void takeHit(Player p);

    /**
     * Updates the internal clock of the enemy.
     */
    void tick();
}
