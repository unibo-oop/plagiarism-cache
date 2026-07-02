package models;

/**
 * The Character interface is an extension of Entity.
 * It establishes contracts for entities that can be killed.
 */
public interface Character extends Entity {

    /**
     * Get current health of entity.
     * 
     * @return current health of entity.
     */
    int getHealth();

    /**
     * Set health for entity.
     * 
     * @param value the amount of health for entity
     */
    void setHealth(int value);

}