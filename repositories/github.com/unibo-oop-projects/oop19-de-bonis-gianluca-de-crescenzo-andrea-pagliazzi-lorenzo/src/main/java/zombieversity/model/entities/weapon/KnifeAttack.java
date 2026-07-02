package zombieversity.model.entities.weapon;

import javafx.geometry.Point2D;
import zombieversity.model.entities.EntityType;

/**
 * Standard attack of a {@link ShortRangeWeapon}.
 */
public class KnifeAttack extends AbstractAttack {

    private static final double HEIGHT = 30; 
    private static final double WIDTH = 10;
    private final Movement trajectory;

    /**
     * @param from
     *          The point where the attack is generated.
     * @param towards
     *          The point towards the attack is headed.
     * @param damage
     *          The damage assigned to the bullet that will be dealt to the enemy.
     */
    public KnifeAttack(final Point2D from, final Point2D towards, final int damage) {
        super(from, damage, EntityType.MELEE_ATTACK);
        this.setBBox(WIDTH, HEIGHT);
        this.trajectory = new AngularMovement(from, towards, HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Movement getMovement() {
        return this.trajectory;
    }

}
