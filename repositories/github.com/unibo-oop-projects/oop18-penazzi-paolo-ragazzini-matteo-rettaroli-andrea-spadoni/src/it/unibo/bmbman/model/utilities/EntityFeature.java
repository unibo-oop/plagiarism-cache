package it.unibo.bmbman.model.utilities;
/**
 * Possible feature of each entity.
 */
public enum EntityFeature {
    /**
     * The entity can be crossed.
     */
    WALKABLE,
    /**
     * The entity cannot be crossed.
     */
    UNWALKABLE,
    /**
     * The entity can be destroyed.
     */
    BREAKABLE,
    /**
     * The entity cannot be destroyed.
     */
    UNBREAKABLE;
}
