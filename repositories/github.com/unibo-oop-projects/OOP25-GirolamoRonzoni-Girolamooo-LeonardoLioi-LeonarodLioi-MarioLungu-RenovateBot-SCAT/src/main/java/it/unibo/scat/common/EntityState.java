package it.unibo.scat.common;

/**
 * Interface for the Entity class.
 */
public interface EntityState {

    /**
     * EntityType getter.
     * 
     * @return the type of entity.
     */
    EntityType getType();

    /**
     * Alive boolean getter.
     * 
     * @return true if the entity is alive, false otherwise.
     */
    boolean isAlive();

    /**
     * Position getter.
     * 
     * @return the position.
     */
    Position getPosition();

    /**
     * Width getter.
     * 
     * @return the width.
     */
    int getWidth();

    /**
     * Height getter.
     * 
     * @return height.
     */
    int getHeight();

    /**
     * Health getter.
     * 
     * @return the health.
     */
    int getHealth();
}
