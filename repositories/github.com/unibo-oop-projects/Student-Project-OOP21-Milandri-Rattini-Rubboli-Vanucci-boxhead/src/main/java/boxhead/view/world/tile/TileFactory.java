package boxhead.view.world.tile;

import java.util.Map;
import java.util.Set;

import javafx.geometry.Point2D;

/**
 * Interface that models a factory for Tiles
 */
public interface TileFactory {
	/**
	 * Method to setup the size of a tile
	 *
	 * @param size
	 */
	void setTileSize(double size);

	/**
	 * Method to generate a tile
	 *
	 * @param t   - index of the tile
	 * @param pos - position of the tile
	 * @param s   - size
	 * @return Tile
	 */
	Tile createTile(int t, Point2D pos, double s);

	/**
	 * Method to generate a set of tile
	 *
	 * @param tile
	 * @param s
	 * @return
	 */

	Set<Tile> createTiles(Map<Point2D, Integer> tile, double s);

}
