/**
 * 
 */
package model.entities;

/**
 * Enumeration to define the status of an {@link model.entities.Entity}.
 */
public enum EntityStatus {
    /**
     * The Entity's current health is more than 0.
     */
    ALIVE,
    /**
     * The Entity's current health is 0.
     */
    DEAD
}
