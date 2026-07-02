package controller.file;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import controller.file.utilities.ResourceManager;
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
 * The implementation of the File Controller. This class manage the images,
 * fonts and other files by using utilities.
 */
public final class FileControllerImpl implements FileController {

    @Override
    public Image getSprite(final Sprite sprite) {
        return ResourceManager.getInstance().getSprite(sprite);
    }

    @Override
    public Font getFont(final GameFont font) {
        return ResourceManager.getInstance().getFont(font);
    }

    @Override
    public Image getSceneImage(final SceneImage sceneImage) {
        return ResourceManager.getInstance().getSceneImage(sceneImage);
    }

    @Override
    public List<Block> getStageMap(final StageMap stageMap) {
        return new ArrayList<Block>(ResourceManager.getInstance().getStageMap(stageMap));
    }

    @Override
    public Queue<Enemy> getStageEnemies(final StageEnemies stageEnemies) {
        return new LinkedList<Enemy>(ResourceManager.getInstance().getStageEnemies(stageEnemies));
    }

}
