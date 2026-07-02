package model.entity;

/**
 * generic interface for entities the entity is anything present in a square of
 * the world.
 *
 */
public interface Entity {
    /**
     * Get the {@link EntityTypeName} of the {@link Entity}.
     * @return the type of the entity.
     */
    EntityTypeNameEnum getEntityType();
    /**
     * Get the X of the {@link Entity} on the map.
     * @return the X of the word;
     */
    int getX();
    /**
     * Get the Y of the {@link Entity} on the map.
     * @return the Y of the word;
     */
    int getY();
}
