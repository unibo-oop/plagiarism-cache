package model.board;

/**
 * 
 */

public class TreasureTile implements Tile {

    /**
     * This is the score value of the Tile.
     */
    private Integer value;
    /**
     * This is the type of the Tile.
     */
    private String type;

    /**
     * Class constructor.
     * 
     * @param value
     *                  Value of the Tile;
     */
    public TreasureTile(final int value) {
        this(value, TileType.TREASURE.tileType());
    }

    /**
     * Class constructor for different kind of treasureTile.
     * 
     * @param value
     *                     Value of the Tile;
     * @param tileType
     *                     Kind of TileType;
     */
    public TreasureTile(final int value, final String tileType) {
        this.value = value;
        this.type = tileType;
    }

    @Override
    public final String getType() {
        return type;
    }

    @Override
    public final Integer getValue() {
        return this.value;
    }

    @Override
    public final String toString() {

        return "I am a " + this.getType().toString() + " tile value " + this.value;
    }

    @Override
    public final void setType(final String tileType) {
        this.type = tileType;
    }

}
