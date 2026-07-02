package it.unibo.model.tiles;

/**
 * The Tile interface represents a tile in a two-dimensional space.
 * Tiles have X and Y coordinates, as well as a string path to an associated image.
 */
public interface Tile {

    /**
     * Gets the X-coordinate value of the Tile.
     *
     * @return The X-coordinate value.
     */
    int getX();

    /**
     * Gets the Y-coordinate value of the Tile.
     *
     * @return The Y-coordinate value.
     */
    int getY();

    /**
     * Gets the string path of the image associated with the Tile.
     *
     * @return The string path of the image.
     */
    String getSrcImage();

}

