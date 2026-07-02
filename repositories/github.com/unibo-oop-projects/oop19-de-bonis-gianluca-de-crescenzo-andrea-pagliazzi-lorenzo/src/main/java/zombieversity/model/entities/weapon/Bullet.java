package zombieversity.model.entities.weapon;

import javafx.geometry.Point2D;
import zombieversity.model.entities.EntityType;

/**
 * Standard attack of any {@link LongRangeWeapon}.
 */
public class Bullet extends AbstractAttack {

    private static final double HEIGHT = 10; 
    private static final double WIDTH = 7;
    private final Movement trajectory;

    /**
     * @param from
     *          The point where the attack is generated.
     * @param towards
     *          The point towards the attack is headed.
     * @param damage
     *          The damage assigned to the bullet that will be dealt to the enemy.
     */
    public Bullet(final Point2D from, final Point2D towards, final int damage) {
        super(from, damage, EntityType.BULLET);
        this.setBBox(WIDTH, HEIGHT);
        this.trajectory = new StraightMovement(from, towards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Movement getMovement() {
        return this.trajectory;
    }

}
