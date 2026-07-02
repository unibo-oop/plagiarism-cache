package rogue.model.creature;

import rogue.model.Entity;

/**
 * An interface modeling a game Creature: Monsters or Player.
 *
 * @param <L> the creature's life
 */
public interface Creature<L extends Life> extends Entity {

    /**
     * @return the creature's life
     */
    L getLife();

}
