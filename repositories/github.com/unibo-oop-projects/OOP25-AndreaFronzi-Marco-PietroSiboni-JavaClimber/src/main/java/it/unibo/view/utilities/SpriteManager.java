package it.unibo.view.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 * Manages the loading and retrieval of sprites for the game.
 */
public class SpriteManager {

    private static final Logger LOGGER = Logger.getLogger(SpriteManager.class.getName());

    /**
     * A map that associates each {@link SpriteEnum} with its corresponding
     * {@link BufferedImage}.
     */
    private final Map<SpriteEnum, BufferedImage> sprites = new EnumMap<>(SpriteEnum.class);

    /**
     * Constructor for the SpriteManager. It initializes the sprite map by
     * loading the resources.
     */
    public SpriteManager() {
        loadResources();
    }

    /**
     * Loads the sprite resources into the sprites map.
     */
    private void loadResources() {
        sprites.put(SpriteEnum.DOODLER_LEFT, this.load("doodle_left.png"));
        sprites.put(SpriteEnum.DOODLER_RIGHT, this.load("doodle_right.png"));
        sprites.put(SpriteEnum.ENEMY, this.load("enemy.png"));
        sprites.put(SpriteEnum.GADGET, this.load("gadget.png"));
        sprites.put(SpriteEnum.BROKEN_PLATFORM, this.load("broken_platform.png"));
        sprites.put(SpriteEnum.GREEN_PLATFORM, this.load("green_platform.png"));
        sprites.put(SpriteEnum.HORIZONTAL_MOVE_PLATFORM, this.load("horizontal_move_platform.png"));
        sprites.put(SpriteEnum.COIN, this.load("coin.png"));
        sprites.put(SpriteEnum.SUB_RIGHT, this.load("sub_right.png"));
        sprites.put(SpriteEnum.SUB_LEFT, this.load("sub_left.png"));
        sprites.put(SpriteEnum.BASKET_RIGHT, this.load("basket_right.png"));
        sprites.put(SpriteEnum.BASKET_LEFT, this.load("basket_left.png"));
        sprites.put(SpriteEnum.ASTRO_RIGHT, this.load("astro_right.png"));
        sprites.put(SpriteEnum.ASTRO_LEFT, this.load("astro_left.png"));
        sprites.put(SpriteEnum.NINJA_RIGHT, this.load("ninja_right.png"));
        sprites.put(SpriteEnum.NINJA_LEFT, this.load("ninja_left.png"));
        sprites.put(SpriteEnum.SOCCER_RIGHT, this.load("soccer_right.png"));
        sprites.put(SpriteEnum.SOCCER_LEFT, this.load("soccer_left.png"));
        sprites.put(SpriteEnum.BUNNY_RIGHT, this.load("bunny_right.png"));
        sprites.put(SpriteEnum.BUNNY_LEFT, this.load("bunny_left.png"));
        sprites.put(SpriteEnum.FRANK_RIGHT, this.load("frank_right.png"));
        sprites.put(SpriteEnum.FRANK_LEFT, this.load("frank_left.png"));
        sprites.put(SpriteEnum.FROZEN_RIGHT, this.load("frozen_right.png"));
        sprites.put(SpriteEnum.FROZEN_LEFT, this.load("frozen_left.png"));
        sprites.put(SpriteEnum.GHOST_RIGHT, this.load("ghost_right.png"));
        sprites.put(SpriteEnum.GHOST_LEFT, this.load("ghost_left.png"));
        sprites.put(SpriteEnum.ICE_RIGHT, this.load("ice_right.png"));
        sprites.put(SpriteEnum.ICE_LEFT, this.load("ice_left.png"));
        sprites.put(SpriteEnum.JUNGLE_RIGHT, this.load("jungle_right.png"));
        sprites.put(SpriteEnum.JUNGLE_LEFT, this.load("jungle_left.png"));
    }

    /**
     * Loads an image from the specified path and returns it as a
     * {@link BufferedImage}.
     *
     * @param path the image path
     * @return the loaded {@link BufferedImage}, or null if an error occurs
     */
    private BufferedImage load(final String path) {
        try {
            return ImageIO.read(new File("src/main/resources/" + path));
        } catch (final IOException e) {
            LOGGER.log(Level.WARNING, "Error loading sprite: " + path, e);
            return null; // O gestisci errore
        }
    }

    /**
     * Gets the sprite associated with the given {@link SpriteEnum} key.
     *
     * @param key the {@link SpriteEnum} key for the desired sprite
     * @return the corresponding {@link BufferedImage}, or null if the key is
     *         not found
     */
    public BufferedImage get(final SpriteEnum key) {
        return sprites.get(key);
    }
}
