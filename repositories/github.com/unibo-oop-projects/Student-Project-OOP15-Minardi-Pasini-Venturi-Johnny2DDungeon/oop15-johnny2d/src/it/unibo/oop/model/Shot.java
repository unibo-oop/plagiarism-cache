package it.unibo.oop.model;

/**
 * Interface representing a shootable entity like a {@link Bullet} or a special
 * bullet.
 */
public interface Shot {

    /**
     * Gets the remaining distance of the shot
     * 
     * @return the distance 
     */
    double getRemainingDistance();
}