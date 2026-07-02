package bzzbomber.view.gamefield;

import java.awt.Point;
import java.io.Serializable;

/**
 * Represents a structure of game field grid 2D.
 */
public interface GameField extends Serializable {

    /**
     * @param p
     *            Position of a new tile.
     * @param t
     *            Tile to insert.
     */
    void addTile(Point p, Tile t);

    /**
     * @param p
     *            Position of a tile to be removed.
     */
    void removeTile(Point p);

    /**
     * Cleans the whole map from every Tile.
     */
    void cleanMap();

    /**
     * @param p
     *            Position of a desired Tile.
     * @return A Tile if the position was correct.
     */
    Tile getTile(Point p);

    /**
     * @return Whole map which can or can't contain Tiles.
     */
    Tile[][] getMap();

    /**
     * @return Number of rows it this grid 2D of Tiles.
     */
    int getRows();

    /**
     * @return Number of columns it this grid 2D of Tiles.
     */
    int getColumns();

}
