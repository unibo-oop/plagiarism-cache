package it.unibo.bmbman.view.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Class to manage a Sprite in the application.
 */
public class Sprite {
    private final BufferedImage image;
    /**
     * Create a {@code Sprite}.
     * @param sheet the selected spriteSheet
     * @param x coordinate of the specific sprite
     * @param y coordinate of the specific sprite
     * @param dimension size of the sprite
     */
    public Sprite(final SpriteSheet sheet, final int x, final int y, final int dimension) {
        image = sheet.getSprite(x, y, dimension);
    }
    /**
     * Used to get sprite's image.
     * @return the image
     */
    public BufferedImage getBufferedImage() {
        return image;
    }
    /**
     * use to convert bufferedImage in Image type.
     * @return the image in image Java.awt type
     */
    public Image getImage() {
        return (Image) image;
    }
}
