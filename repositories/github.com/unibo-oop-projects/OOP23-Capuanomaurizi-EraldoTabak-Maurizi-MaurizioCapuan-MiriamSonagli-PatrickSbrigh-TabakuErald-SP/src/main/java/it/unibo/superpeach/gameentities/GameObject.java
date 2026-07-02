package it.unibo.superpeach.gameentities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.graphics.Texturer;

/**
 * Interface of a game object.
 * @author  Maurizio Capuano
 */
public interface GameObject {

    /**
     * @param g
     */
    void render(Graphics g);

    /**
     * Runtime update method for every renderized object on tile.
     */
    void tick();

    /**
     * @return texturer object assigned to the game object
     */
    Texturer getTexturer();

    /**
     * @return a Rectangle object created to wrap the whole object
     */
    Rectangle getBoundingBox();

    /**
     * @return x position of the object in tile
     */
    int getX();

    /**
     * @return y position of the object in tile
     */
    int getY();

    /**
     * @return object height
     */
    int getHeight();

    /**
     * @return object width
     */
    int getWidth();

    /**
     * @return object scale (depends everytime on game scale)
     */
    int getScale();

    /**
     * @return image sprites assigned to the onject ad got from the Texturer
     */
    BufferedImage[] getSprites();

    /**
     * @param x new x value set for the object on the tile
     */
    void setX(int x);

    /**
     * @param y new y value set for the object on the tile
     */
    void setY(int y);

    /**
     * @param height updated object height
     */
    void setHeight(int height);

    /**
     * @param width updated object width
     */
    void setWidth(int width);

    /**
     * @param scale updated object scale
     */
    void setScale(int scale);

    /**
     * @param sprites images to draw on the graphic object
     */
    void setSprites(BufferedImage[] sprites);
}
