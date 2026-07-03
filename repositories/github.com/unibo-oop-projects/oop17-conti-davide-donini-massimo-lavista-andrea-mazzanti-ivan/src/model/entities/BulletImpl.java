package model.entities;

import javafx.scene.shape.Shape;
import model.entities.properties.Velocity;

/**
 * Represent the bullet of the entity shoot.
 */
public final class BulletImpl extends AbstractEntity implements Bullet {

    private final double damage;
    private final BulletType bulletType;

    /**
     * 
     * @param velocity
     *            initial entity's velocity.
     * @param shape
     *            initial entity's shape.
     * @param damage
     *          the damage of the bullet.
     * @param bulletType
     *          the type of the bullet.
     */
    public BulletImpl(final Shape shape, final Velocity velocity, final double damage, 
            final BulletType bulletType) {
        super(shape, velocity);
        this.damage = damage;
        this.bulletType = bulletType;
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public BulletType getBulletType() {
        return this.bulletType;
    }

}
