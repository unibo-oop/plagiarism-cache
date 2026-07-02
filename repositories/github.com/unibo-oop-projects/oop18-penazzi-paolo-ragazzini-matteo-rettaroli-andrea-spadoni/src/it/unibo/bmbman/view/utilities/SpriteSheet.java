package it.unibo.bmbman.view.utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Class to manage each spriteSheet.
 *
 */
public class SpriteSheet {
    private BufferedImage sheet;
    /**
     *Load a spriteSheet.
     *@param path of the specific spriteSheet
     */
    public SpriteSheet(final String path) {
        try {
            sheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
 * Select a part of the spriteSheet.
 * @param x coordinate of the specific sprite
 * @param y coordinate of the specific sprite
 * @param dimension size of the sprite
 * @return the image of the sprite 
 */
    public BufferedImage getSprite(final int x, final int y, final int dimension) {
        return sheet.getSubimage(x * dimension - dimension, y * dimension - dimension, dimension, dimension);
    }

}
