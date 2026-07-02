package javawulf.model.enemy;

import javawulf.model.AbstractEntity;
import javawulf.model.BoundingBox;
import javawulf.model.Coordinate;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.map.Map;
import javawulf.model.player.Player;

/**
 * Implementation of the Enemy interface.
 */
public abstract class EnemyImpl extends AbstractEntity implements Enemy {

    /**
     * Creates an Enemy.
     * 
     * @param position the position of the enemy when created
     */
    public EnemyImpl(final Coordinate position) {
        super(position, CollisionType.ENEMY, DEFAULT_SPEED);
    }

    /**
     * Moves the enemy.
     * 
     * @param p the player
     * @param m the map
     */
    @Override
    public abstract void move(Player p, Map m);

    /**
     * Makes the enemy take a hit.
     * 
     * @param p the player
     */
    @Override
    public abstract void takeHit(Player p);

    /**
     * Updates the internal clock of the enemy.
     */
    @Override
    public void tick() {

    }

    @Override
    protected final boolean control(final BoundingBox box) {
        return box.getCollisionType().equals(CollisionType.SWORD)
                && this.getBounds().getCollisionType().equals(CollisionType.ENEMY);
    }

    @Override
    protected final CollisionType originalCollisionType() {
        return CollisionType.ENEMY;
    }

}
