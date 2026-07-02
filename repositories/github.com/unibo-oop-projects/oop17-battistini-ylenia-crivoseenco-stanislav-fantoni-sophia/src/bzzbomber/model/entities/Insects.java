package bzzbomber.model.entities;

import java.util.Set;
import bzzbomber.controller.collision.InsectCollision;
import bzzbomber.model.utilities.Direction;

/**
 * This interface set method to create Insects and move that.
 *
 */

public interface Insects extends LivingCreature {

    /**
     * This method choose a random direction.
     * 
     * @return random direction
     */
    Direction getRandomDirection();

    /**
     * Gets insects's collision.
     * 
     * @return insects's collision
     */

    InsectCollision getEnemyCollision();

    /**
     * This method move the insects. This method 'll called when the game started.
     * 
     * @param blockSet
     *          The set of block.
     * @param bombSet
     *          The set of bomb.
     * @param hero
     *          The hero.
     */
    void move(Set<Block> blockSet, Set<Bomb> bombSet, BzzBomber hero);

}
