package bzzbomber.view.gamefield;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Represents a graphical representation of model entities.
 */
public interface Tile extends Serializable {

    /**
     * @return image of the tile
     */
    BufferedImage getImage();

}
