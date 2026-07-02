package bubbleshooter.model.collision;

import bubbleshooter.model.bubble.Bubble;
/**
 * Class which represent a collision between two {@link Bubble}.
 */
public class Collision {

    private final Bubble shootingBubble;
    private final Bubble collided;

    /**
     * @param shootingBubble The shootingBubble of the game.
     * @param collided The collided {@link Bubble} of the grid.
     */
    public Collision(final Bubble shootingBubble, final Bubble collided) {
        this.shootingBubble = shootingBubble;
        this.collided = collided;
    }

    /**
     * @return the shootingBubble of the game.
     */
    public final Bubble getShootingBubble() {
        return this.shootingBubble;
    }

    /**
     * @return the grid's {@link Bubble} collided.
     */
    public final Bubble getCollided() {
        return this.collided;
    }
}
