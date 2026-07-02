package it.tbt.model.entities;

/**
 * Generic entity.
 */
public interface Entity extends Comparable<Entity> {

    /**
     * Return the entity name.
     * @return the entity name
     */
    String getName();
}
