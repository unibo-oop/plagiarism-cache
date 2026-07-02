package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import utilities.FrameSizeUtils;

/**
 * Class that loads a sprite sheet from path.
 */
public class SpriteSheet {

    private static final int REAL_SPRITE_SIZE = 50;
    /**
     * It sets the sprite size used in game.
     */
    public static final int SPRITE_SIZE_IN_GAME = FrameSizeUtils.getEdgeLength() / FrameSizeUtils.NUM_TILES;

    private final BufferedImage sheet;

    /**
     * Constructor.
     * @param path : sprite sheet location
     * @throws IOException : while loading
     */
    public SpriteSheet(final String path) throws IOException {
        this.sheet = ImageIO.read(getClass().getResource(path));
    }

    /**
     * Get a single sprite from sheet.
     * @param x : coordinate to select the sprite
     * @param y : coordinate to select the sprite
     * @return a buffered image of the sprite
     */
    public BufferedImage getSingleSprite(final int x, final int y) {
        return this.sheet.getSubimage(x * REAL_SPRITE_SIZE - REAL_SPRITE_SIZE,
                                      y * REAL_SPRITE_SIZE - REAL_SPRITE_SIZE,
                                      REAL_SPRITE_SIZE, REAL_SPRITE_SIZE);
    }

    /**
     * Getter for sprite size to draw.
     * @return the sprite size to use in game
     */
    public int getSpriteSize() {
        return SPRITE_SIZE_IN_GAME;
    }
}
