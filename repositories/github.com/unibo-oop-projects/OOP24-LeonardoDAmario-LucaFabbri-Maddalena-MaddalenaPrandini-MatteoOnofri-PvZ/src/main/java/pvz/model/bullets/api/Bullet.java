package pvz.model.bullets.api;

import pvz.model.entities.api.Entity;

/**
 * Represents a bullet entity in the game.
 * A bullet can deal damage to other entities (e.g., zombies).
 */
public interface Bullet extends Entity {

    /**
     * Gets the damage dealt by this bullet.
     *
     * @return the damage value
     */
    int getDamage();

}
