package view.graphics_component.level;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import model.level.types.Level;
import view.graphics.GraphicsLevel;
import view.graphics_util.Cache;
import view.graphics_util.CacheFactoryImpl;
import view.graphics_util.ISpriteLoader;
import view.graphics_util.SpriteSheetLoader;

/**
 * Implementation of {@link GraphicsLevelComponent} for tutorial levels.
 * 
 * This component handles loading the level sprite sheet and level data,
 * mapping the tile indices to their corresponding images, and updating
 * the graphical representation of the level.
 */
public class GrahicsTutlevelComponent implements GraphicsLevelComponent {

    private static final int WIDTH_FRAME = 32;
    private static final int HEIGHT_FRAME = 33;

    private ISpriteLoader loader = new SpriteSheetLoader();
    private List<List<BufferedImage>> allImageLevel;
    private BufferedImage survivorWeapon;
    private static Cache<String, BufferedImage> cacheWeapon = new CacheFactoryImpl().imageCache();

    /**
     * Constructs a new GrahicsTutlevelComponent.
     * 
     * It loads the level sprite sheet and level data, and maps tile indices
     * to their respective images for efficient rendering.
     */
    public GrahicsTutlevelComponent() {
        allImageLevel = this.mapLevelDataToImages(this.loader.loadLevelSprite("levelSprite", WIDTH_FRAME, HEIGHT_FRAME),
                this.loader.getLevelData("levelData"));
    }

    private BufferedImage loadImageWeapon(final String weapon) {
        if (!cacheWeapon.contains(weapon)) {
            BufferedImage loadedImage = loader.loadEntitiesWeapon(weapon);
            cacheWeapon.put(weapon, loadedImage);
            this.survivorWeapon = loadedImage;
            return loadedImage;
        }
        this.survivorWeapon = cacheWeapon.get(weapon);
        return this.survivorWeapon;
    }

    /**
     * Maps the 2D list of tile indices to a 2D list of corresponding images.
     * 
     * @param levelSprites the list of available tile images from the sprite sheet
     * @param levelData    a 2D list representing the layout of tile indices for the
     *                     level
     * @return a 2D list of BufferedImages corresponding to the tiles of the level
     */
    private List<List<BufferedImage>> mapLevelDataToImages(List<BufferedImage> levelSprites,
            List<List<Integer>> levelData) {
        List<List<BufferedImage>> result = new ArrayList<>();

        for (List<Integer> row : levelData) {
            List<BufferedImage> imageRow = new ArrayList<>();
            for (Integer tileIndex : row) {
                if (tileIndex >= 0 && tileIndex < levelSprites.size()) {
                    imageRow.add(levelSprites.get(tileIndex));
                } else {
                    imageRow.add(null);
                }
            }
            result.add(imageRow);
        }
        return result;
    }

    /**
     * Updates the graphical representation of the level by drawing it
     * using the provided {@link GraphicsLevel} interface.
     * 
     * @param lvl    the level to render
     * @param gryLvl the graphics interface responsible for drawing the level
     */
    @Override
    public void update(final Level lvl, final GraphicsLevel gryLvl) {
        this.loadImageWeapon(lvl.getSurvivorOnLevel().getWeapon().getClass().getSimpleName());
        gryLvl.drawLevel(lvl, this.allImageLevel,this.survivorWeapon);
    }

}
