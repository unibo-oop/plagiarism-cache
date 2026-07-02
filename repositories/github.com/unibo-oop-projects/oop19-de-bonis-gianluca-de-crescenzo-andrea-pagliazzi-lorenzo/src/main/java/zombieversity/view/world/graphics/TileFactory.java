package zombieversity.view.world.graphics;

import java.util.Map;
import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
/**
 * Interface that models a factory for Tiles.
 *
 */
public interface TileFactory {

    /**
     * Method to setup the size of the tile. Effects only new ones.
     * @param size
     */
    void setTileProperties(double size);
    /**
     * Method to generate the tile of the given type and properties.
     * @param t - index of the tile ( it represents the type).
     * @param p - position the tile relative.
     * @param s - size of the tile
     * @return Tile
     */
    Tile createTile(Integer t, Point2D p, double s);
    /**
     * Method to create a collection of tiles, from given indexes.
     * @param tile
     * @param s
     * @return Set<Tile>
     */
    Set<Tile> createTiles(Map<Point2D, Integer> tile, double s);
    /**
     * Generate the set of tile of the given size with the same id.
     * @param id
     * @param w
     * @param h
     * @param s
     * @return Image
     */
    Image createUniqueBackground(int id, double w, double h, double s);

    /**
     * To change factory properties so to change the graphics content need to be replaced the tileset.
     * @param tSet - the new graphics package.
     */
    void setTileSet(TileSet tSet);

}
