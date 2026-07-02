package graphics;

import java.awt.Image;

/**
 * Class that represents a single sprite.
 */
public class Sprite {

    private final Image image;

    /**
     * Constructor.
     * @param sheet : where to take the single sprite
     * @param x : coordinate to select the sprite
     * @param y : coordinate to select the sprite
     */
    public Sprite(final SpriteSheet sheet, final int x, final int y) {
        this.image = sheet.getSingleSprite(x, y).getScaledInstance(SpriteSheet.SPRITE_SIZE_IN_GAME,
                                                                   SpriteSheet.SPRITE_SIZE_IN_GAME,
                                                                   Image.SCALE_SMOOTH);
    }

    /**
     * Getter for the sprite image.
     * @return the sprite image
     */
    public Image getBufferedImage() {
        return this.image;
    }
}
