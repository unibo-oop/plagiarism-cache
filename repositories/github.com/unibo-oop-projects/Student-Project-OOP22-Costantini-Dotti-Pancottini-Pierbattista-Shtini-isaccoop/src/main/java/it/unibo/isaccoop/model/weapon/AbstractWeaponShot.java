package it.unibo.isaccoop.model.weapon;

import it.unibo.isaccoop.graphics.GraphicsComponent;
import it.unibo.isaccoop.model.common.AbstractMapElement;
import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.common.Vector2D;

/**
 * AbstractWeaponShot abstract class that implements WeaponShot interface, it models
 * a common container for weapon shots.
 * */
public abstract class AbstractWeaponShot extends AbstractMapElement implements WeaponShot {

    private static final Double SPEED = 2.0;
    private final Vector2D shotVector;

    /**
     *  Constructor for {@link EnemyWeaponShot} class.
     *
     *  @param startPosition start {@link EnemyWeaponShot} position as a {@link Pair}
     *  @param direction shot vector
     *  @param gComponent graphic component
     * */
    public AbstractWeaponShot(final Point2D startPosition, final Vector2D direction, final GraphicsComponent gComponent) {
        super(startPosition, ElementsRadius.BULLET, gComponent);
        this.shotVector = direction;
    }

    /**
     * Get Weapon shots speed.
     *
     * @return weapon shots speed
     * */
    protected Double getSpeed() {
        return AbstractWeaponShot.SPEED;
    }

    /**
     * Get Weapon shot vector.
     *
     * @return weapon shot vector
     * */
    protected Vector2D getShotVector() {
        return this.shotVector;
    }

}
