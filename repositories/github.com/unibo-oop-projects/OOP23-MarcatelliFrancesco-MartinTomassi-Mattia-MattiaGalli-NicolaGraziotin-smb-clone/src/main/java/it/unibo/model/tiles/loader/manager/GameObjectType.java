package it.unibo.model.tiles.loader.manager;

/**
 * Enumeration representing types of game objects.
 */
public enum GameObjectType {

    /**
     * Object type: Saws.
     */
    SAWS("saws"),

    /**
     * Object type: Platforms.
     */
    PLATFORMS("rectangle");

    /**
     * TagName of the objects.
     */
    private final String nameObjects;

    /**
     * Constructor for the GameObjectType enumeration.
     *
     * @param nameObjects The name of the object
     */
    GameObjectType(final String nameObjects) {
        this.nameObjects = nameObjects;
    }

    /**
     * Returns a string representation of this object.
     *
     * @return A string representing the object
     */
    @Override
    public String toString() {
        return this.nameObjects;
    }

}
