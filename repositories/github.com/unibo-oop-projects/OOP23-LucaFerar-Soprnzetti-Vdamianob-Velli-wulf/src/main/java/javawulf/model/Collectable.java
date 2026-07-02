package javawulf.model;

import javawulf.model.player.Player;

/**
 * Interface for collectable objects which can be collected by the player.
 * They could be items or powerups.
 */
public interface Collectable extends GameElement {

    /**
     * Collects the object, applies the effect to the player and adds the points to
     * the player.
     * 
     * @param p the player who collects the object
     */
    void collect(Player p);

    /**
     * Applies the effect of the collectable to the player.
     * 
     * @param p the player who gets the effect of the object
     */
    void applyEffect(Player p);
}
