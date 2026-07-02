package model.entity;

/**
 * Models an entity that may or may not exist at a given time.
 */
public interface ConditionalEntity {

    /**
     * Returns true if this entity still exists.
     * @return true if this entity still exists; false otherwise.
     */
    boolean is();
    /**
     * Returns true if this entity doesn't exist anymore.
     * @return true if this entity doesn't exist anymore; false otherwise.
     */
    default boolean isnt() {
        return !this.is();
    }

}
