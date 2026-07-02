package controller.file.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import enums.GameFont;
import enums.SceneImage;
import enums.Sprite;
import enums.StageEnemies;
import enums.StageMap;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import model.enemy.Enemy;
import model.entities.Block;

/**
 * Class that manage all file and their opening.
 */
public final class ResourceManager {

    // Instance of the class.
    private static final ResourceManager SINGLETON = new ResourceManager();

    // Maps for files.
    private final Map<GameFont, Font> fonts = new HashMap<GameFont, Font>();
    private final Map<SceneImage, Image> sceneImages = new HashMap<SceneImage, Image>();
    private final Map<Sprite, Image> sprites = new HashMap<Sprite, Image>();
    private final Map<StageEnemies, Queue<Enemy>> stageEnemies = new HashMap<StageEnemies, Queue<Enemy>>();
    private final Map<StageMap, List<Block>> stageMaps = new HashMap<StageMap, List<Block>>();

    /**
     * Method that implements the Singleton Pattern. It returns only one instance of
     * the class, created only one time.
     * 
     * @return the instance of the class.
     */
    public static ResourceManager getInstance() {
        return SINGLETON;
    }

    /*
     * Empty constructor of the class that manage the files.
     */
    private ResourceManager() {
    }

    /**
     * Method that load all files and put them in map collections.
     */
    public void loadAllFiles() {
        for (final GameFont fontEnum : GameFont.values()) {
            final Font fontValue = FontUtility.getInstance().getFont(fontEnum);
            fonts.put(fontEnum, fontValue);
        }
        for (final SceneImage sceneImageEnum : SceneImage.values()) {
            final Image sceneImageValue = SceneImageUtility.getInstance().getSceneImage(sceneImageEnum);
            sceneImages.put(sceneImageEnum, sceneImageValue);
        }
        for (final Sprite spriteEnum : Sprite.values()) {
            final Image spriteValue = SpriteUtility.getInstance().getSprite(spriteEnum);
            sprites.put(spriteEnum, spriteValue);
        }
        for (final StageEnemies stageEnemiesEnum : StageEnemies.values()) {
            final Queue<Enemy> stageEnemiesValue = EnemyUtility.getInstance().getEnemyList(stageEnemiesEnum);
            stageEnemies.put(stageEnemiesEnum, stageEnemiesValue);
        }
        for (final StageMap stageMapEnum : StageMap.values()) {
            final List<Block> stageMapValue = BlockUtility.getInstance().getBlockList(stageMapEnum);
            stageMaps.put(stageMapEnum, stageMapValue);
        }
    }

    /**
     * Method that return the requested font.
     * 
     * @param font the font to be returned.
     * @return the font requested.
     */
    public Font getFont(final GameFont font) {
        return this.fonts.get(font);
    }

    /**
     * Method that return the requested scene image.
     * 
     * @param sceneImage the scene image to be returned.
     * @return the scene image requested.
     */
    public Image getSceneImage(final SceneImage sceneImage) {
        return this.sceneImages.get(sceneImage);
    }

    /**
     * Method that return the requested sprite.
     * 
     * @param sprite the sprite to be returned.
     * @return the sprite requested.
     */
    public Image getSprite(final Sprite sprite) {
        return this.sprites.get(sprite);
    }

    /**
     * Method that return the requested enemy list.
     * 
     * @param stageEnemies the enemy lists to be returned.
     * @return the enemy list requested.
     */
    public Queue<Enemy> getStageEnemies(final StageEnemies stageEnemies) {
        return this.stageEnemies.get(stageEnemies);
    }

    /**
     * Method that return the requested block list.
     * 
     * @param stageMap the block list to be returned.
     * @return the block list requested.
     */
    public List<Block> getStageMap(final StageMap stageMap) {
        return this.stageMaps.get(stageMap);
    }

}
