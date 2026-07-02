package it.unibo.oop.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unibo.oop.exceptions.SpritesNotSplittableException;
import it.unibo.oop.utilities.Direction;
import static it.unibo.oop.utilities.Direction.*;

/**
 * A class representing a sprite sheet. The sprite sheets are generated with
 * MMORPG Maker XB Sprite Generator.
 * 
 * @see <a href="http://www.mmorpgmakerxb.com/p/characters-sprites-generator">
 *      MMORPG Maker XB Sprite Generator</a>
 */
public class SpriteSheet {

    private BufferedImage sheet;

    /**
     * Constructs a new {@link SpriteSheet}.
     * 
     * @param sheetName
     *            the name of the sprite sheet
     */
    public SpriteSheet(final String sheetName) {
        try {
            this.sheet = ImageLoader.load(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage grabSprite(final int x, final int y, final int width, final int height) {
        return this.sheet.getSubimage(x, y, width, height);
    }

    /**
     * Splits every sprite in the {@link SpriteSheet}. It considers only the
     * first sprite for every row (only one animation).
     * 
     * @param spritesWidth
     *            the width of each sprite in the {@link SpriteSheet}
     * @param spritesHeight
     *            the height of each sprite in the {@link SpriteSheet}
     * @return a {@link Map} of {@link BufferedImage} with all the sprites in
     *         the {@link SpriteSheet} mapped with their {@link Direction}
     */
    public Map<Direction, BufferedImage> split(final int spritesWidth, final int spritesHeight)
            throws SpritesNotSplittableException {
        final Map<Direction, BufferedImage> sprites = new HashMap<>();
        final boolean isSplitted = ((this.sheet.getHeight() % spritesHeight == 0)
                && (this.sheet.getWidth() % spritesWidth == 0)) ? true : false;
        if (isSplitted) {
            for (int y = 0, currentRow = 0; y < this.sheet.getHeight(); y += spritesHeight, currentRow++) {
                switch (currentRow) {
                case 0:
                    sprites.put(DOWN, this.grabSprite(0, y, spritesWidth, spritesHeight));
                    break;
                case 1:
                    sprites.put(LEFT, this.grabSprite(0, y, spritesWidth, spritesHeight));
                    break;
                case 2:
                    sprites.put(RIGHT, this.grabSprite(0, y, spritesWidth, spritesHeight));
                    break;
                case 3:
                    sprites.put(UP, this.grabSprite(0, y, spritesWidth, spritesHeight));
                    break;
                default:
                    sprites.put(DOWN, this.grabSprite(0, y, spritesWidth, spritesHeight));
                    break;
                }
            }
        } else {
            throw new SpritesNotSplittableException();
        }
        return sprites;
    }
}