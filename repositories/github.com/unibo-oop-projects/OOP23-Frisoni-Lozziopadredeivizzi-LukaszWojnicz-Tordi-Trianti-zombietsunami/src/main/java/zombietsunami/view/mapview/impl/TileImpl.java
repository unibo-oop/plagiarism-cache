package zombietsunami.view.mapview.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import zombietsunami.view.mapview.api.Tile;

/**
 * This class implements the Tile interface
 * {@link zombietsunami.view.mapview.api.Tile}.
 */
public final class TileImpl implements Tile {

    private BufferedImage image;

    @Override
    public void setImage(final String string) {
        final Logger logger = Logger.getLogger(TileImpl.class.getName());
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(string));
        } catch (IOException e) {
            logger.severe("Error reading the image: " + e.getMessage());
        }
    }

    @Override
    @SuppressFBWarnings(justification = "I directly need to get the BufferedImage fild"
    + " from this class because if i create a new BufferedImage that is a clone of the"
    + " one i need, this will slow the game down")
    public BufferedImage getImage() {
        return this.image;
    }

}
