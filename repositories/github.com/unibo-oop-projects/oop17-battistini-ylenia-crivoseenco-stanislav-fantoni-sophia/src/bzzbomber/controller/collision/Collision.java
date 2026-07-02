package bzzbomber.controller.collision;

import java.awt.Rectangle;
import java.util.Set;

import bzzbomber.model.entities.Block;
import bzzbomber.model.entities.Bomb;
import bzzbomber.model.entities.Entity;
import bzzbomber.model.entities.Explosion;
import bzzbomber.model.utilities.Direction;

/**
 * Manage the general aspects of collision for every @LivingCreature, such as
 * collision with the following object: blocks, bombs, explosions.
 *
 */

public interface Collision {

    /**
     * Control a block collision.
     * 
     * @param blockSet
     *            The set of blocks in @GameField .
     * @return true if there's a collision, false otherwise.
     */

    boolean blockCollision(Set<Block> blockSet);

    /**
     * Control a bomb collision.
     * 
     * @param bombSet
     *            The set of bombs in @GameField .
     * @return true if there's a collision, false otherwise.
     */

    boolean bombCollision(Set<Bomb> bombSet);

    /**
     * Control exploded bombs collision.
     * 
     * @param explosionSet
     *            The set of exploded bombs in @GameField .
     * @return true if there's a collision, false otherwise.
     */

    boolean explosionCollision(Set<Explosion> explosionSet);

    /**
     * This method update the position of entity's collisionBox.
     * 
     * @param direction
     *            The direction of the entity
     */
    void updateEntityHitbox(Direction direction);

    /**
     * Getter of entity.
     * 
     * @return the @Entity .
     */
    Entity getEntity();

    /**
     * Getter of collision box.
     * 
     * @return the @Entity collision box.
     */
    Rectangle getCollisionBox();

}
