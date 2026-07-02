package bzzbomber.controller.collision;

import bzzbomber.model.entities.BzzBomber;

/**
 * Interface for the specific monster collisions extends @Collision.
 *
 */
public interface InsectCollision extends Collision {
    /**
     * Control the collision with @Hero.
     * 
     * @param hero
     *            The @Hero.
     * @return true if there's a collision, false otherwise.
     */
    boolean heroCollision(BzzBomber hero);

}
