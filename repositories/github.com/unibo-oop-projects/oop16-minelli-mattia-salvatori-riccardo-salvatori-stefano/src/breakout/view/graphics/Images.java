package breakout.view.graphics;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import breakout.model.entities.PowerUpEffect;
import breakout.view.utils.Utils;
import javafx.scene.image.Image;

/**
 * A container for game images. This class imports all images when is loaded.
 *
 */
public final class Images {
    private static Images singleton;
    private static final String STANDARD_IMAGE_EXTENSION = ".png";
    private static final String BACKGROUND_IMAGE_EXTENSION = ".jpg";
    private static final String BACKGROUNDS_PATH = "/Images/Backgrounds/";
    private static final String POWERUP_PATH = "/Images/PowerUps/";

    private final Map<Backgrounds, Image> backgroundsImages = new HashMap<>();
    private final Map<PowerUpEffect, Image> powerUpsImages = new HashMap<>();
    private final Map<ColoredEntities, Map<Colors, List<Image>>> coloredImages = new HashMap<>();

    /**
     * Enum of entities that can have different colors.
     */
    public enum ColoredEntities {

        /**
         * Simple Bricks images.
         */
        SIMPLE_BRICKS("/Images/Bricks/Simple/Brick", STANDARD_IMAGE_EXTENSION),

        /**
         * Hard Bricks images.
         */
        HARD_BRICKS("/Images/Bricks/Hard/Hard", STANDARD_IMAGE_EXTENSION),

        /**
         * Unbreakable Bricks images.
         */
        UNBREAKABLE_BRICKS("/Images/Bricks/Unbreakable/Unbreakable", STANDARD_IMAGE_EXTENSION),

        /**
         * Paddles images.
         */
        PADDLES("/Images/Paddles/Paddle", STANDARD_IMAGE_EXTENSION),

        /**
         * Balls images.
         */
        BALLS("/Images/Balls/Ball", STANDARD_IMAGE_EXTENSION);

        /**
         * Set of colored enetities that can be damaged.
         */
        private static final EnumSet<ColoredEntities> DAMAGEABLE = EnumSet.of(HARD_BRICKS);
        private final String path;
        private final String imageExtension;

        ColoredEntities(final String path, final String imageFormat) {
            this.path = path;
            this.imageExtension = imageFormat;
        }

        /**
         * @return the path to the folder where the images are stored
         */
        private String getPath() {
            return this.path;
        }

        /**
         * 
         * @return the format of the image
         */
        private String getExtension() {
            return this.imageExtension;
        }

    }

    private Images() {
        // Load all background Images
        Arrays.asList(Backgrounds.values()).forEach(background -> backgroundsImages.put(background,
                new Image(Utils.getPath(BACKGROUNDS_PATH + background.getName() + BACKGROUND_IMAGE_EXTENSION))));

        // Load all powerUp Images
        Arrays.asList(PowerUpEffect.values()).forEach(powerUp -> powerUpsImages.put(powerUp,
                new Image(Utils.getPath(POWERUP_PATH + powerUp.name() + STANDARD_IMAGE_EXTENSION))));

    }

    /**
     * @param entity
     *            the entity you want the image
     * @param color
     *            the color of the image
     * @return the image of the entity with the given color
     */
    public Image getColoredImage(final ColoredEntities entity, final Colors color) {
        if (this.coloredImages.containsKey(entity) && this.coloredImages.get(entity).containsKey(color)) {
            return this.coloredImages.get(entity).get(color).get(0);
        } else {
            this.loadImage(entity, color);
        }
        return coloredImages.get(entity).get(color).get(0);

    }

    /**
     * @param entity
     *            the entity you want the image
     * @param color
     *            the color of the image
     * @param damage
     *            the damage you want to applay to the image
     * @return the image of the entity with the given color
     */
    public Image getColoredImageWithDamage(final ColoredEntities entity, final Colors color, final int damage) {
        if (this.coloredImages.containsKey(entity) && this.coloredImages.get(entity).containsKey(color)) {
            return this.coloredImages.get(entity).get(color).get(damage);
        } else {
            this.loadImage(entity, color);
        }
        return coloredImages.get(entity).get(color).get(damage);

    }

    /**
     * 
     * @param background
     *            the background you want the image
     * @return the image of the background in input
     */
    public Image getBackgroundImage(final Backgrounds background) {
        return backgroundsImages.get(background);
    }

    /**
     * 
     * @param powerUp
     *            the powerUp you want the image
     * @return the image of the powerUp in input
     */
    public Image getPowerUpImage(final PowerUpEffect powerUp) {
        return powerUpsImages.get(powerUp);
    }

    /**
     * @return the instance of the singleton
     */
    public static Images getImages() {
        if (Objects.isNull(singleton)) {
            singleton = new Images();
        }
        return singleton;
    }

    private void loadImage(final ColoredEntities entity, final Colors color) {
        final Map<Colors, List<Image>> colorsImageMap = new HashMap<>();
        // It can be that the entity is damageable.
        // By default this method returns the image with 0 damage but loads
        // also the other damaged images.
        if (ColoredEntities.DAMAGEABLE.contains(entity)) {
            final Image loadImage0 = new Image(
                    Utils.getPath(entity.getPath() + "0_" + color.name() + entity.getExtension()));
            final Image loadImage1 = new Image(
                    Utils.getPath(entity.getPath() + "1_" + color.name() + entity.getExtension()));
            final Image loadImage2 = new Image(
                    Utils.getPath(entity.getPath() + "2_" + color.name() + entity.getExtension()));
            colorsImageMap.put(color, Arrays.asList(loadImage0, loadImage1, loadImage2));
        } else {
            final Image loadImage = new Image(
                    Utils.getPath(entity.getPath() + "_" + color.name() + entity.getExtension()));
            colorsImageMap.put(color, Arrays.asList(loadImage));
        }
        this.coloredImages.put(entity, colorsImageMap);

    }

}
