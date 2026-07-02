package javawulf.model;

import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.player.Player;

/**
 * AbstractCollectable is an abstract class that implements the Collectable.
 */
public abstract class AbstractCollectable extends GameObject implements Collectable {

    private final int points;

    /**
     * Creates a new collectable object.
     * 
     * @param position the position of the object
     * @param points   the points the object gives to the player
     */
    public AbstractCollectable(final Coordinate position, final int points) {
        super(position, CollisionType.COLLECTABLE);
        this.points = points;
    }

    /**
     * Returns the points the object gives to the player.
     * 
     * @return the points the object gives to the player
     */
    public final int getPoints() {
        return this.points;
    }

    /**
     * Checks if the object is colliding with the player and applies the effect
     * of the collectable to the player, while also adding the points to the
     * player's score and changing the collision type of the object to inactive.
     * 
     * @param p
     */
    @Override
    public final void collect(final Player p) {
        if (this.getBounds().isCollidingWith(p.getBounds().getCollisionArea())) {
            this.applyEffect(p);
            p.getScore().addPoints(this.points);
            this.getBounds().changeCollisionType(CollisionType.INACTIVE);
        }
    }

    /**
     * Applies the effect of the collectable to the player.
     * 
     * @param p the player who gets the effect of the object
     */
    @Override
    public abstract void applyEffect(Player p);

}
