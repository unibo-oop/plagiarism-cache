package controller.file;

import java.util.List;
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
 * The interface for the file controller.
 */
public interface FileController {

    /**
     * Method that return the sprite passed as enumeration parameter.
     * 
     * @param sprite the sprite to be returned.
     * @return the sprite chosen.
     */
    Image getSprite(Sprite sprite);

    /**
     * Method that return the font passed as enumeration parameter.
     * 
     * @param font the font to be returned.
     * @return the font chosen.
     */
    Font getFont(GameFont font);

    /**
     * Method that return the scene image passed as enumeration parameter.
     * 
     * @param sceneImage the scene image font to be returned.
     * @return the scene image chosen.
     */
    Image getSceneImage(SceneImage sceneImage);

    /**
     * Method that return the map with all blocks of the given stage.
     * 
     * @param stageMap the stage map to be returned.
     * @return the stage map with its block.
     */
    List<Block> getStageMap(StageMap stageMap);

    /**
     * Method that return the enemies list of the given stage.
     * 
     * @param stageEnemies the stage enemies to be returned
     * @return the stage enemies.
     */
    Queue<Enemy> getStageEnemies(StageEnemies stageEnemies);
}
