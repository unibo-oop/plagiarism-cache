package javawulf.model.map;

/**
 * All kind of tiles. Tiles are used to compose game map.
 */
public enum TileType {
    /** Wall is the default unwalkable tile. */
    WALL(false),
    /** Room is a kind of tile used for compose rooms (so it is walkable). */
    ROOM(true),
    /** Used to compose the central room, where the game will be completed by player. */
    CENTRAL_ROOM(true),
    /** Crossable tile for ending the game (if conditions are respected). */
    PORTAL(true),
    /** Corridor is a tile for link rooms with a walkable kind of cell. */
    CORRIDOR(true);

    private final boolean crossable;
    /** Default pixel dimension of a tile (both width and height: it's a square). */
    public static final int TILE_DIMENSION = 24;

    TileType(final boolean crossable) {
        this.crossable = crossable;
    }

    /**
     * 
     * @return (true/false) if the tile type can be crossed by entities.
     */
    public boolean isCrossable() {
        return this.crossable;
    }
}
