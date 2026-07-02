package view.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import controller.file.utilities.BlockUtility;
import controller.file.utilities.EnemyUtility;
import controller.file.utilities.FontUtility;
import controller.file.utilities.SceneImageUtility;
import controller.file.utilities.SpriteUtility;
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
 * Class for test on the loading files.
 */
class FilesTest {

    // Magic Numbers.
    private static final int ENEMIES_FOR_EACH_LIST = 20;

    @Test
    protected void openAllFontsTest() {
        for (final GameFont font : GameFont.values()) {
            final Font f = FontUtility.getInstance().getFont(font);
            if (f == null) {
                fail();
            }
        }
    }

    @Test
    protected void openAllSpritesTest() {
        for (final Sprite sprite : Sprite.values()) {
            final Image s = SpriteUtility.getInstance().getSprite(sprite);
            if (s == null) {
                fail();
            }
        }
    }

    @Test
    protected void openAllSceneImagesTest() {
        for (final SceneImage sceneImage : SceneImage.values()) {
            final Image si = SceneImageUtility.getInstance().getSceneImage(sceneImage);
            if (si == null) {
                fail();
            }
        }
    }

    @Test
    protected void openAllStageMap() {
        for (final StageMap stageMap : StageMap.values()) {
            final List<Block> sm = BlockUtility.getInstance().getBlockList(stageMap);
            if (sm == null || sm.isEmpty()) {
                fail();
            }
        }
    }

    @Test
    protected void openAllStageEnemies() {
        for (final StageEnemies stageEnemies : StageEnemies.values()) {
            final Queue<Enemy> se = EnemyUtility.getInstance().getEnemyList(stageEnemies);
            if (se == null || se.size() != ENEMIES_FOR_EACH_LIST) {
                fail();
            }
        }
    }

}
