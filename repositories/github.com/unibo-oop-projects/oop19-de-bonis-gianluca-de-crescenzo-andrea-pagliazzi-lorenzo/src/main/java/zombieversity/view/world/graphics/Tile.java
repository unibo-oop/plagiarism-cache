package zombieversity.view.world.graphics;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;



public interface Tile {

    /**
     * Method to get the content of the Tile.
     * @return Image - representing the tile.
     */
    Image getTile();
    /**
     * Method to get the size of a Tile, assuming is a Square is necessary only the size of an edge.
     * @return double - effective size.
     */
    double getSize();
    /**
     * Method to scale the Tile in view phase.
     * @param scale - multiplicator for scaling the tile.
     */
    void setRenderScale(double scale);
    /**
     * Method to get current Tile scale.
     * @return double - multiplicator active.
     */
    double getRenderScale();
    /**
     * Method to get the position of a Tile relative to the view.
     * @return Point2D - realtive Pos
     */
    Point2D getRelativePos();
    /**
     * Method to get the absolute position of the Tile.
     * @return Point2D - absolute Pos.
     */
    Point2D getAbsolutePos();
    /**
     * Deprecated don't use that.
     * @return double - Point2D.getX()
     */
    double getX();
    /**
     * Deprecated don't use that.
     * @return double - Point2D.getY()
     */
    double getY();
}
