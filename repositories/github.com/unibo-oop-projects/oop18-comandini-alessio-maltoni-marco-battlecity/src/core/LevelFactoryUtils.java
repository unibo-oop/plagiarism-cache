package core;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import controller.file.FileController;
import controller.file.FileControllerImpl;
import enums.Stage;
import enums.StageEnemies;
import enums.StageMap;
import model.enemy.Enemy;
import model.entities.Block;

/**
 * A factory that creates the game levels.
 */
public final class LevelFactoryUtils {

    /*
     * Private constructor.
     */
    private LevelFactoryUtils() {
    }

    /**
     * Method that create a level and return it in order to the given stage.
     * 
     * @param stage the stage referred to the level.
     * @return the chosen level.
     */
    public static Level getLevel(final Stage stage) {
        final FileController fc = new FileControllerImpl();
        Level level = null;
        List<Block> stageMap = null;
        Queue<Enemy> stageEnemies = null;
        // Load the map.
        for (final StageMap map : StageMap.values()) {
            if (stage.getStageNumber() == map.getStageNumber()) {
                stageMap = fc.getStageMap(map);
            }
        }
        // Load the enemies.
        for (final StageEnemies enemies : StageEnemies.values()) {
            if (stage.getStageNumber() == enemies.getStageNumber()) {
                stageEnemies = fc.getStageEnemies(enemies);
            }
        }
        // Create the level.
        level = new LevelImpl(stage, stageMap, stageEnemies);

        return level;
    }

    /**
     * Method that return all the levels in a queue.
     * 
     * @return all the levels in a queue.
     */
    public static Queue<Level> getAllLevels() {
        final Queue<Level> levels = new LinkedList<Level>();
        for (final Stage stage : Stage.values()) {
            levels.add(LevelFactoryUtils.getLevel(stage));
        }
        return levels;
    }

}
