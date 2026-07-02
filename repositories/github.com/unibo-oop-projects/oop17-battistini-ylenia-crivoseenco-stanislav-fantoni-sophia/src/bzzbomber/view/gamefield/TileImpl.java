package bzzbomber.view.gamefield;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import bzzbomber.view.TileImg;

/**
 * Implementation of @Tile interface.
 */
public class TileImpl implements Tile {

    private static final long serialVersionUID = 1L;
    private BufferedImage image;

    /**
     * Constructor of TileImpl.
     * 
     * @param tileImage
     *            The image.
     */
    public TileImpl(final TileImg tileImage) {
        loadImage(tileImage.getVal().getSecond());
    }

    private void loadImage(final String path) {
        try {
            final URL fileURL = getClass().getClassLoader().getResource(path);
            final BufferedImage img = ImageIO.read(fileURL);
            this.image = img;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final BufferedImage getImage() {
        return this.image;
    }

}
