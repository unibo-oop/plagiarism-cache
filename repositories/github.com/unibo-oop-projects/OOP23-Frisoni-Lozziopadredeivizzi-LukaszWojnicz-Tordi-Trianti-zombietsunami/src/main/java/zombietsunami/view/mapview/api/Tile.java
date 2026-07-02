package zombietsunami.view.mapview.api;

import java.awt.image.BufferedImage;

/**
 * Allows you to consider a single tile of the game map with its image.
 */
public interface Tile {

    /**
     * This method assigns at the BufferedImage fild its image.
     * 
     * @param string is the path to take the image resource.
     */
    void setImage(String string);

    /**
     * @return the setted image of the Tile class.
     */
    BufferedImage getImage();
}
