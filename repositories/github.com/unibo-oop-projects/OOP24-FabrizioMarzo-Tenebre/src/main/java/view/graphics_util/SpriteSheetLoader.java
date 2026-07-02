package view.graphics_util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.image.Raster;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Loads sprite sheets and extracts animations, sprites, shadows, munitions, and
 * level data.
 */
public class SpriteSheetLoader implements ISpriteLoader {

    // Paths to sprite resources
    private static final String SURVIVOR_PATH = "/sprite_sheet/survivor/";
    private static final String ZOMBIE_PATH = "/sprite_sheet/zombie/";
    private static final String MUNITION_PATH = "/sprite_sheet/armory/munition/";
    private static final String WEAPON_PATH = "/sprite_sheet/armory/weapon/";
    private static final String LEVEL_PATH = "/sprite_sheet/level/";

    private static final String LEVEL_OBJECT_PATH = "/level_object/";
    private final ImportImageFactory factory = new ImportImageFactoryImpl();
    private final ImportImage loaderPNG = factory.loaderPNG();

    /**
     * Loads animations for a survivor from a sprite sheet.
     * 
     * @param nameSurvivor the sprite sheet name
     * @param width_frame  width of each frame
     * @param height_frame height of each frame
     * @return list of animation frames grouped by animation state
     */
    @Override
    public List<List<BufferedImage>> loadSurvivorAnimations(final String nameSurvivor, final int width_frame,
            final int height_frame) {
        return loadAnimations(SURVIVOR_PATH, nameSurvivor, width_frame, height_frame);
    }

    /**
     * Loads animations for a zombie from a sprite sheet.
     * 
     * @param nameZombie   the sprite sheet name
     * @param width_frame  width of each frame
     * @param height_frame height of each frame
     * @return list of animation frames grouped by animation state
     */
    @Override
    public List<List<BufferedImage>> loadZombieAnimations(final String nameZombie, final int width_frame,
            final int height_frame) {
        return loadAnimations(ZOMBIE_PATH, nameZombie, width_frame, height_frame);
    }

    /**
     * Loads sprites for a level from a sprite sheet.
     * 
     * @param nameLevelSprite the sprite sheet name
     * @param width_frame     width of each frame
     * @param height_frame    height of each frame
     * @return list of sprites
     */
    @Override
    public List<BufferedImage> loadLevelSprite(final String nameLevelSprite, final int width_frame,
            final int height_frame) {
        return loadSprite(LEVEL_PATH, nameLevelSprite, width_frame, height_frame);
    }

    /**
     * Loads the shadow image for entities.
     * 
     * @param nameShadow the shadow image name
     * @return shadow image
     */
    @Override
    public BufferedImage loadEntitiesShadow(final String nameShadow) {
        Optional<BufferedImage> subImage = getBBoxImage(loaderPNG.imp(LEVEL_OBJECT_PATH + nameShadow).get());
        return subImage.get();
    }

    /**
     * Loads the weapon image for entities.
     * 
     * @param nameWeapon the weapon image name
     * @return weapon image
     */
    public BufferedImage loadEntitiesWeapon(final String nameWeapon) {
        Optional<BufferedImage> subImage = getBBoxImage(loaderPNG.imp(WEAPON_PATH + nameWeapon).get());
        return subImage.get();
    }

    /**
     * Loads the image of a munition.
     * 
     * @param nameMunition the munition image name
     * @return munition image
     */
    @Override
    public BufferedImage loadMunition(final String nameMunition) {
        Optional<BufferedImage> subImage = getBBoxImage(loaderPNG.imp(MUNITION_PATH + nameMunition).get());
        return subImage.get();
    }

    /**
     * Retrieves level tile data from an image.
     * 
     * @param nameLevelData the level data image name
     * @return 2D list of tile values extracted from the image's red channel
     */
    @Override
    public List<List<Integer>> getLevelData(final String nameLevelData) {
        var img = loaderPNG.imp(LEVEL_PATH + nameLevelData);
        List<List<Integer>> levelData = new ArrayList<>();

        Raster raster = img.get().getRaster();
        int numBands = raster.getNumBands();

        for (int j = 0; j < img.get().getHeight(); j++) {
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < img.get().getWidth(); i++) {
                int[] pixel = new int[numBands];
                raster.getPixel(i, j, pixel);

                int valueRed = pixel[0];
                row.add(valueRed);
            }
            levelData.add(row);
        }

        return levelData;
    }

    /**
     * Calculates the number of columns and rows in a sprite sheet image based on
     * frame dimensions.
     * 
     * @param width_frame  width of a single frame
     * @param height_frame height of a single frame
     * @param img          sprite sheet image
     * @return pair containing number of columns (left) and rows (right)
     */
    private Pair<Integer, Integer> numColRow(final int width_frame, final int height_frame, final BufferedImage img) {

        int columns = img.getWidth() / width_frame;
        int rows = img.getHeight() / height_frame;

        return Pair.of(columns, rows);
    }

    /**
     * Loads animation frames grouped by rows from a sprite sheet.
     * 
     * @param pathPrefix   base path of the sprite sheet
     * @param name         sprite sheet name
     * @param width_frame  frame width
     * @param height_frame frame height
     * @return list of animation frame lists by row
     */
    private List<List<BufferedImage>> loadAnimations(final String pathPrefix, final String name, final int width_frame,
            final int height_frame) {
        var img = loaderPNG.imp(pathPrefix + name);

        // Check if the sprite image is load corretly
        if (!img.isPresent()) {
            System.err.println("Image not found: " + pathPrefix + name);
            return new ArrayList<>(); // Return the list empty of animations
        }

        // If the image is load corretly found his col and row
        var numCR = this.numColRow(width_frame, height_frame, img.get());
        return this.loadSpriteSets(numCR, width_frame, height_frame, img.get());
    }

    /**
     * Loads sprites from a sprite sheet (single list).
     * 
     * @param pathPrefix   base path of the sprite sheet
     * @param name         sprite sheet name
     * @param width_frame  frame width
     * @param height_frame frame height
     * @return list of sprites
     */
    private List<BufferedImage> loadSprite(final String pathPrefix, final String name, final int width_frame,
            final int height_frame) {
        var imgcity = loaderPNG.imp(pathPrefix + name);

        if (!imgcity.isPresent()) {
            System.err.println("Image not found: " + pathPrefix + name);
            return new ArrayList<>(); // Return the list empty of animations
        }

        var numCR = this.numColRow(width_frame, height_frame, imgcity.get());
        return this.loadSprites(numCR, width_frame, height_frame, imgcity.get());
    }

    /**
     * Extracts individual sprites from an image based on frame size.
     * 
     * @param numColRow    pair of columns and rows
     * @param width_frame  frame width
     * @param height_frame frame height
     * @param img          source image
     * @return list of sprite images
     */
    private List<BufferedImage> loadSprites(final Pair<Integer, Integer> numColRow, final int width_frame,
            final int height_frame, final BufferedImage img) {

        List<BufferedImage> allSprites = new ArrayList<>();
        var numCol = numColRow.getLeft();
        var numRow = numColRow.getRight();

        for (int j = 0; j < numRow; j++) {
            for (int i = 0; i < numCol; i++) {
                BufferedImage frame = img.getSubimage(
                        i * width_frame,
                        j * height_frame,
                        width_frame,
                        height_frame);

                Optional<BufferedImage> subImage = getBBoxImage(frame);
                if (subImage.isEmpty())
                    break;

                allSprites.add(subImage.get());
            }
        }

        return allSprites;
    }

    /**
     * Extracts animations frames organized by rows from a sprite sheet image.
     * 
     * @param numColRow    pair of columns and rows
     * @param width_frame  frame width
     * @param height_frame frame height
     * @param img          source image
     * @return list of animation frame lists by row
     */
    private List<List<BufferedImage>> loadSpriteSets(final Pair<Integer, Integer> numColRow, final int width_frame,
            final int height_frame, final BufferedImage img) {

        List<List<BufferedImage>> allAnimations = new ArrayList<>();
        var numCol = numColRow.getLeft();
        var numRow = numColRow.getRight();

        // Begin of the first row
        for (int j = 0; j < numRow; j++) {
            List<BufferedImage> aniRow = new ArrayList<>(); // Create the first list of image

            for (int i = 0; i < numCol; i++) {
                BufferedImage frame = img.getSubimage(
                        i * width_frame,
                        j * height_frame,
                        width_frame,
                        height_frame);

                Optional<BufferedImage> subImage = getBBoxImage(frame);
                if (subImage.isEmpty())
                    break;

                aniRow.add(subImage.get());
            }
            allAnimations.add(aniRow);
        }
        return allAnimations;
    }

    /**
     * Finds the bounding box of non-transparent pixels within a frame and returns
     * that subimage.
     * 
     * @param img the image frame to analyze
     * @return optional containing the cropped image if non-transparent pixels
     *         found; empty otherwise
     */
    private Optional<BufferedImage> getBBoxImage(final BufferedImage img) {

        int width = img.getWidth();
        int height = img.getHeight();
        int top = height, bottom = 0, left = width, right = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);

                if (rgb != 0) {
                    if (x < left)
                        left = x;
                    if (x > right)
                        right = x;
                    if (y < top)
                        top = y;
                    if (y > bottom)
                        bottom = y;
                }
            }
        }

        if (top == height && bottom == 0 && left == width && right == 0)
            return Optional.empty();

        BufferedImage frame = img.getSubimage(left, top, right - left + 1, bottom - top + 1);
        return Optional.of(frame);
    }

}
