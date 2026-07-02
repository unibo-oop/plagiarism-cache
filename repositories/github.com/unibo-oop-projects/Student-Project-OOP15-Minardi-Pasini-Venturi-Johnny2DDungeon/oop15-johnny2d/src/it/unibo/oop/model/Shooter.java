package it.unibo.oop.model;

/**
 * An interface that can shoot {@link Bullet} form a weapon or from himself.
 */
public interface Shooter {

    /**
     * The method that fires the projectile
     */
    void shoot();
}