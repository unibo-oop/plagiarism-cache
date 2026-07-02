package model.board;

/**
 * This enumerator is made for use for distinguish the different kind of tile.
 */

public enum TileType {

    /**
     * Tipe Treasure (usually with a value between 0 and 15).
     */
    TREASURE("treasure"),
    /**
     * Tile TreasureGroup (represent the sum of falling down treasure from player that is not returned to boat in time).
     */
    TREASUREGROUP("treasure group"),
    /**
     * Tile Empty, rapresent a tile of kind empty (used as a singleton causes is not necessary to handle it in a
     * different way).
     */
    EMPTY("empty");
    /**
     * Color of the specific call.
     */
    private String tileType;

    /**
     * Builder of a class call.
     */
    TileType(final String tileType) {
        this.tileType = tileType;
    }

    /**
     * Return the tile type of the specific entity call.
     * @return the saved color.
     */
    public String tileType() {
        return this.tileType;
    }
    /**
     * @param test
     *                 the string to test
     * @return A boolean that represent if tested string is correct.
     */
    public static boolean contains(final String test) {

        for (final TileType c : TileType.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }

}
