package boxhead.model.entities.zombies;

import javafx.geometry.Point2D;
import boxhead.model.entities.AbstractHealthEntity;
import boxhead.model.entities.EntityType;
import boxhead.model.entities.utils.Direction;

/**
 * Zombie properties modeling
 *
 */
public class Zombie extends AbstractHealthEntity {

    /**
     * Zombie BB width
     */
    private static final int WIDTH = 32;
    /**
     * Zombie BB height
     */
    private static final int HEIGHT = 32;

    private final int damage;

    /**
     * 
     * @param startingPos position where Zombie starts
     * @param speed movement speed
     * @param maxHp starting health
     * @param damage damage inflict to player
     */
    public Zombie(final Point2D spawnPoint, Direction direction, final double speed, EntityType entityType, final int maxHp, final int damage) {
        super(new Point2D(speed, speed), Direction.WEST, spawnPoint, EntityType.ZOMBIE, maxHp);
        super.setBoundingBox(WIDTH, HEIGHT);
        this.damage = damage;
    }

    /**
     * 
     * @return damage inflict to player
     */
    public final int getDamage() {
        return this.damage;
    }

}
