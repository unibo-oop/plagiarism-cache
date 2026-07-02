package model.board;

/**
 * This interface defines the methods that must return the logic of a tile.
 */
public interface Tile {

    /**
     * This is a getter for the value of the tile.
     * 
     * @return an int value
     */
    Integer getValue();

    /**
     * This is a getter for the TileType of the tile.
     * 
     * @return the TileType of the tile
     */

    String getType();

    /**
     * This is a setterr for the TileType of the tile.
     * 
     * @param tileType
     *                kind of tyle rapresented
     */

    void setType(String tileType);

}
