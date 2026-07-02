package powpaw.map.model.api;

import javafx.geometry.Point2D;

/**
 * Interface for BlockImpl class representing the block (terrain) entity.
 * 
 * @author Giacomo Grassetti
 */

public interface Block {

    /**
     * Getter for position of the block.
     * 
     * @return position of the block (Point2D).
     */
    Point2D getPosition();

    /**
     * Setter for X axis position of the block.
     * 
     * @param x double value of X axis.
     */
    void setX(double x);

    /**
     * Setter for Y axis position of the block.
     * 
     * @param y double value of X axis.
     */
    void setY(double y);

    /**
     * Getter for hitbox of the block.
     * 
     * @return hitbox of the block (BlockHitbox).
     */
    BlockHitbox getHitbox();

    /**
     * Getter for width of the block.
     * 
     * @return width of the block (double).
     */
    double getWidth();

    /**
     * Setter for width of the block.
     * 
     * @param width block value (double)
     */
    void setWidth(double width);

    /**
     * Getter for height of the block.
     * 
     * @return height of the block (double).
     */
    double getHeight();

    /**
     * Setter for heigth of the block.
     * 
     * @param height block value (double)
     */
    void setHeight(double height);
}
