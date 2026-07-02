package zombieversity.view.world;

import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import zombieversity.view.world.graphics.Tile;
/**
 * Interface to model a worldView layered, first layer on bottom the background, second layer on top the objects.
 *
 */
public interface WorldView {
    /**
     * Get the full map as a collection of Tiles.
     * @return Set<Tile>
     */
    Set<Tile> getMap();
    /**
     * Set rendering scale, for a resizable view.
     * @param scale
     */
    void setScale(double scale);
    /**
     * Method to get all the images representing each tile of the background and it's relative position, not ready to be rendered because position is absolute.
     * @return Set<Pair<Point2D, Image> - could be used a map but map doesn't give stream option.
     */
    Set<Pair<Point2D, Image>> renderBackground();
    /**
     * Method to get all the images representing each tile of the objects and it's relative position, not ready to be rendered because position is absolute.
     * @return Set<Pair<Point2D, Image> - could be used a map but map doesn't give stream option.
     */
    Set<Pair<Point2D, Image>> renderMap();
    /**
     * Method to get the approximation of the map in colors as a minimap.
     * @param start - specify which region of map i want to see
     * @param end - specify which region of the map i want to see.
     * @param offset - offset to translate relative position to absolute.
     * @return Set<Pair<Point2D, Color>> -
     */
    Set<Pair<Point2D, Color>> renderMiniMap(Point2D start, Point2D end, Point2D offset);
}
