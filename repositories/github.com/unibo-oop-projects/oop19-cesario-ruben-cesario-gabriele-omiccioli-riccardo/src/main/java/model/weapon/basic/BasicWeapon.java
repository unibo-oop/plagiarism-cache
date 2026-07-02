package model.weapon.basic;

import java.util.Collection;
import java.util.Set;

import model.entity.EntityID;
import model.entity.MovingEntityImpl;
import model.weapon.Weapon;
import utilities.math.Point2D;
import utilities.math.Vector2D;
import utilities.math.Vector2DImpl;

/**
 *  Basic implementation of the interface weapon.
 */
public class BasicWeapon implements Weapon {

    private static final StandardProjectileTemplate DEFAULT_PROJECTILE_TEMPLATE = new StandardProjectileTemplate(3, 0.4, 30);
    private static final int DEFAULT_RATE_OF_FIRE = 10;
    private final StandardProjectileTemplate projectileTemplate;
    private final int rateOfFire;

    public BasicWeapon() {
        this(DEFAULT_PROJECTILE_TEMPLATE, DEFAULT_RATE_OF_FIRE);
    }
    BasicWeapon(final StandardProjectileTemplate projectileTemplate, final int rateOfFire) {
        this.projectileTemplate = projectileTemplate;
        this.rateOfFire = rateOfFire;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Projectile> shoot(final Point2D origin, final double direction) {
        return Set.of(new StandardProjectile(origin, direction, new Vector2DImpl(projectileTemplate.speed, direction),
                                             projectileTemplate.radius, projectileTemplate.damage));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRateOfFire() {
        return this.rateOfFire;
    }

    /**
     *  Projectiles shot by a BasicWeapon.
     */
    public class StandardProjectile extends MovingEntityImpl implements Projectile {

        private final int damage;
        private boolean hasHit;

        public StandardProjectile(final Point2D position, final double radiantAngle, final Vector2D speed, final double radius, final int damage) {
            super(position, radius, radiantAngle, speed);
            this.damage = damage;
            this.hasHit = false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public EntityID getID() {
            return EntityID.PROJECTILE_STANDARD;
        }

        /**
         * {@inheritDoc}
         * A StandardProjectile is destroyed when it collides for the first time.
         */
        @Override
        public boolean is() {
            return !this.hasHit;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void hit() {
            this.hasHit = true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getDamage() {
            return this.damage;
        }

    }

    /**
     *  A container for the shot-independent properties of a StandardProjectile.
     */
    public static class StandardProjectileTemplate {

        private final double speed;
        private final double radius;
        private final int damage;

        public StandardProjectileTemplate(final double speed, final double radius, final int damage) {
            this.speed = speed;
            this.radius = radius;
            this.damage = damage;
        }

        /*SETTERS & GETTERS------------------------------------------------*/
        /**
         * Returns the initial speed of projectiles of this template.
         * @return the initial speed of projectiles of this template.
         */
        public double getSpeed() {
            return speed;
        }

        /**
         * Returns the radius of the circles occupied by projectiles of this template.
         * @return the radius of the circles occupied by projectiles of this template
         */
        public double getRadius() {
            return radius;
        }

        /**
         * Returns the damage dealt by projectiles of this template.
         * @return the damage dealt by projectiles of this template.
         */
        public int getDamage() {
            return damage;
        }
        /*-----------------------------------------------------------------*/
    }

}
