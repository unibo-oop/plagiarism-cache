package it.unibo.model.tiles;

/**
 * Implementation of the Tile interface representing a tile in the game.
 */
public class TileImpl implements Tile {

    private final int x;
    private final int y;
    private final String srcImage;

    /**
     * Constructs a new tile with the specified coordinates and image source.
     *
     * @param x         The x-coordinate of the tile.
     * @param y         The y-coordinate of the tile.
     * @param srcImage  The source image of the tile.
     */
    public TileImpl(final int x, final int y, final String srcImage) {
        this.x = x;
        this.y = y;
        this.srcImage = srcImage;
    }

    /**
     * Retrieves the x-coordinate of the tile.
     *
     * @return The x-coordinate of the tile.
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * Retrieves the y-coordinate of the tile.
     *
     * @return The y-coordinate of the tile.
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Retrieves the source image of the tile.
     *
     * @return The source image of the tile.
     */
    @Override
    public String getSrcImage() {
        return this.srcImage;
    }

}
