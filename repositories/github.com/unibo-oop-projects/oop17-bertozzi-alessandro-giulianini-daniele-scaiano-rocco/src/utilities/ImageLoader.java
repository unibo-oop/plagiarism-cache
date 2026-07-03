package utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import game.ID;
import game.SpecialEffect.SpecialEffectType;
import game.buffs.PowerUpType;
import view.EndScreen;
import view.GameWindow;
import view.MenuPanel;

/**
 * The class that loads all the images used in the game.
 */
public final class ImageLoader {
    /**
     * This is the only instance of this class.
     */
    private static final ImageLoader LOADER = new ImageLoader();

    private static final int DIMENSION_OF_SPRITES_IN_SPRITESHEET = 192;
    /**
     * Images for all the entity except powerUps, specialEffects and bullets which are handled separately.
     */
    private final Map<ID, List<Image>> entityImages = new HashMap<>();
    /**
     * Static images for powerUps.
     */
    private final Map<Pair<ID, PowerUpType>, List<Image>> powerUpImages = new HashMap<>();
    /**
     * Animations for powerUps.
     */
    private final Map<Pair<ID, PowerUpType>, List<Image>> powerUpAnimations = new HashMap<>();
    /**
     * Animations for specialEffects.
     */
    private final Map<Pair<ID, SpecialEffectType>, List<Image>> specialEffectsAnimations = new HashMap<>();
    /**
     * Images for backgrounds.
     */
    private final Map<String, List<Image>> backgroundImages = new HashMap<>();
    /**
     * Images for bullets.
     */
    private final Map<Pair<ID, ID>, List<Image>> bulletImages = new HashMap<>();

    private ImageLoader() { }

    /**
     * Loads all the images in the res folder.
     */
    public void findImages() {
                URL imgURL = ImageLoader.class.getResource("/menuBackground.jpg");
                this.backgroundImages.put(MenuPanel.TITLE, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/1stShip.png");
                this.entityImages.put(ID.PLAYER, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/2ndShip.png");
                this.entityImages.put(ID.PLAYER2, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/ob1.png");
                this.entityImages.put(ID.SMP_OBSTACLE, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/ob2.png");
                this.entityImages.put(ID.BNC_OBSTACLE, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/ob3.png");
                this.entityImages.put(ID.ENL_OBSTACLE, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/ob4.png");
                this.entityImages.put(ID.TML_OBSTACLE, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/ob5.png");
                this.entityImages.put(ID.ENL_BNC_OBSTACLE, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/ob6.png");
                this.entityImages.put(ID.TML_BNC_OBSTACLE, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/ob7.png");
                this.entityImages.put(ID.TML_ENL_OBSTACLE, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/ob8.png");
                this.entityImages.put(ID.TML_ENL_BNC_OBSTACLE, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/blueBeam.png");
                this.bulletImages.put(new Pair<>(ID.BULLET, ID.PLAYER), loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/background1.jpg");
                this.backgroundImages.put(GameWindow.TITLE, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/bulletSpeedUp.png");
                this.powerUpAnimations.put(new Pair<>(ID.POWER_UP, PowerUpType.FIRE_RATE_BOOST), loadMultipleImages(imgURL));
                imgURL = ImageLoader.class.getResource("/basicEnemy.png");
                this.entityImages.put(ID.BASIC_ENEMY, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/smartEnemy.png");
                this.entityImages.put(ID.SMART_ENEMY, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/bigEnemy.png");
                this.entityImages.put(ID.BIG_ENEMY, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/fastEnemy.png");
                this.entityImages.put(ID.FAST_ENEMY, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/bossEnemy.png");
                this.entityImages.put(ID.BOSS_ENEMY, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/explosion.png");
                this.specialEffectsAnimations.put(new Pair<>(ID.EFFECT, SpecialEffectType.EXPLOSION), loadMultipleImages(imgURL));
                imgURL = ImageLoader.class.getResource("/galaxy.png");
                this.backgroundImages.put(EndScreen.TITLE, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/galaxy1.png");
                this.backgroundImages.merge(EndScreen.TITLE, loadSingleImage(imgURL), (x, y) -> {
                    final List<Image> list = new ArrayList<>(x);
                    list.addAll(y);
                    return list;
                });
                imgURL = ImageLoader.class.getResource("/nuke.png");
                this.powerUpImages.put(new Pair<>(ID.POWER_UP, PowerUpType.NUKE), loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/powerUp1.png");
                this.powerUpImages.put(new Pair<>(ID.POWER_UP, PowerUpType.FIRE_RATE_BOOST), loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/powerUp2.png");
                this.powerUpImages.put(new Pair<>(ID.POWER_UP, PowerUpType.SPEED_BOOST), loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/powerUp3.png");
                this.powerUpImages.put(new Pair<>(ID.POWER_UP, PowerUpType.HEALTH_RECOVERY), loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/redBeam.png");
                this.bulletImages.put(new Pair<>(ID.BULLET, ID.PLAYER2), loadSingleImage(imgURL));
                this.entityImages.put(ID.SHOOT, loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/shield.png");
                this.powerUpImages.put(new Pair<>(ID.POWER_UP, PowerUpType.SHIELD), loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/snowflake.png");
                this.powerUpImages.put(new Pair<>(ID.POWER_UP, PowerUpType.FREEZE), loadSingleImage(imgURL));
                imgURL = ImageLoader.class.getResource("/speedPowerUp.png");
                this.powerUpAnimations.put(new Pair<>(ID.POWER_UP, PowerUpType.SPEED_BOOST), loadMultipleImages(imgURL));
                imgURL = ImageLoader.class.getResource("/freeze.png");
                this.powerUpAnimations.put(new Pair<>(ID.POWER_UP, PowerUpType.FREEZE), loadSingleImage(imgURL));
    }

    private List<Image> loadSingleImage(final URL url) {
        return Arrays.asList(new ImageIcon(url).getImage());
    }

    private List<Image> loadMultipleImages(final URL url) {
        final List<Image> list = new ArrayList<>();
        final int width;
        final int height;
        final int rows;
        final int cols;
        if (url.toString().endsWith("explosion.png")) {
            width = 100;
            height = 100;
            rows = 8;
            cols = 8;
        } else if (url.toString().endsWith("bulletSpeedUp.png")) {
            width = DIMENSION_OF_SPRITES_IN_SPRITESHEET;
            height = DIMENSION_OF_SPRITES_IN_SPRITESHEET;
            rows = 1;
            cols = 4;
        } else {
            width = DIMENSION_OF_SPRITES_IN_SPRITESHEET;
            height = DIMENSION_OF_SPRITES_IN_SPRITESHEET;
            rows = 2;
            cols = 4;
        }
        try {
            final BufferedImage img = ImageIO.read(url);
            IntStream.rangeClosed(0, rows).forEach(i -> IntStream.rangeClosed(0, cols).forEach(j -> {
                list.add(img.getSubimage(j * width, i * height, width, height));
//                System.out.println("Loaded" + i + " " + j);
            }));
        } catch (final IOException e) {
            System.out.println("Error loading SpriteSheet");
        }
        return list;
    }

    /**
     * @return the only instance of this class
     */
    public static ImageLoader getLoader() {
        return ImageLoader.LOADER;
    }

    /**
     * @return the entityImages
     */
    public Map<ID, List<Image>> getEntityImages() {
        return entityImages;
    }

    /**
     * @return the powerUpImages
     */
    public Map<Pair<ID, PowerUpType>, List<Image>> getPowerUpImages() {
        return powerUpImages;
    }

    /**
     * @return the powerUpAnimations
     */
    public Map<Pair<ID, PowerUpType>, List<Image>> getPowerUpAnimations() {
        return powerUpAnimations;
    }

    /**
     * @return the specialEffectsAnimations
     */
    public Map<Pair<ID, SpecialEffectType>, List<Image>> getSpecialEffectsAnimations() {
        return specialEffectsAnimations;
    }

    /**
     * @return the backgroundImages
     */
    public Map<String, List<Image>> getBackgroundImages() {
        return backgroundImages;
    }

    /**
     * @return the bulletImages
     */
    public Map<Pair<ID, ID>, List<Image>> getBulletImages() {
        return bulletImages;
    }
}
