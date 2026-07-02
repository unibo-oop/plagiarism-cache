package it.unibo.bmbman.model.utilities;
/**
 * Possible types of entities.
 */
public enum EntityType {
    /**
     * It is the player.
     */
    HERO(EntityFeature.UNWALKABLE, EntityFeature.BREAKABLE),
    /**
     * It is basically grass cell.
     */
    TILE(EntityFeature.WALKABLE, EntityFeature.UNBREAKABLE),
    /**
     * It is a part of the maze that the player can break using bombs.
     */
    BLOCK(EntityFeature.UNWALKABLE, EntityFeature.BREAKABLE),
    /**
     * It is an unbreakable part of the maze.
     */
    WALL(EntityFeature.UNWALKABLE, EntityFeature.UNBREAKABLE),
    /**
     * The player's weapon.
     */
    BOMB(EntityFeature.UNWALKABLE, EntityFeature.UNBREAKABLE),
    /**
     * The hero's enemy.
     */
    MONSTER(EntityFeature.UNWALKABLE, EntityFeature.BREAKABLE),
    /**
     * They are bonus or malus hidden under the block that hero can pick.
     */
    POWER_UP(EntityFeature.UNWALKABLE, EntityFeature.UNBREAKABLE); 
    private EntityFeature isWalkable;
    private EntityFeature isBreakable;
    /**
     * Construct an entity type with some features.
     * @param isWalkable specify if the entity can be crossed or not
     * @param isBreakable specify if the entity can be broken or not
     */
    EntityType(final EntityFeature isWalkable, final EntityFeature isBreakable) {
       checkWalkable(isWalkable);
       this.isWalkable = isWalkable;
       checkBreakable(isBreakable);
       this.isBreakable = isBreakable;
    }
    /**
     * Check {@link EntityType} constructor parameter, 
     * it must be a correct EntityFeature: WALKABLE or UNWALKABLE.
     * @param isWalkable possible feature
     */
    private void checkWalkable(final EntityFeature isWalkable) {
        if (isWalkable != EntityFeature.WALKABLE && isWalkable != EntityFeature.UNWALKABLE) {
            throw new IllegalArgumentException("Must specify a pertinent feature");
        }
    }
    /**
     Check {@link EntityType} constructor parameter, 
     * it must be a correct EntityFeature: BREAKABLE or UNBREAKABLE. 
     * @param isBreakable
     */
    private void checkBreakable(final EntityFeature isBreakable) {
        if (isBreakable != EntityFeature.BREAKABLE && isBreakable != EntityFeature.UNBREAKABLE) {
            throw new IllegalArgumentException("Must specify a pertinent feature");
        }
    }
    /**
     * Says if the entity is walkable or not.
     * @return the feature 
     */
    public EntityFeature getIsWalkable() {
        return isWalkable;
    }
    /**
     *  Says if the entity is walkable or not.
     * @return the feature
     */
    public EntityFeature getIsBreakable() {
        return isBreakable;
    }
}

