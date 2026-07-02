package view.level.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;

import model.entities.GameEntityTypes;
import model.entities.components.HealthComponent;
import view.level.GameLevel;
import view.level.factory.LevelDepth;
import view.level.settings.LevelSettings;
import view.level.spawning.SpawningEngineUtil;

/**
 * This class manages the spawn of the levels.
 */
public final class GameEngineUtil {

    private static List<GameLevel> levelList = new ArrayList<>();
    private static GameLevel currentLevel;
    private static int levelCounter;
    private static int nLevelType0;
    private static int nLevelType1;
    private static int nLevelType2;
    private static int counterLevelName;

    /**
     * This method initialize all levels and the list of levels.
     */
    /*public static void initLevels() {
        setNLevelsForType();
        generateLevels();
        initGameLevelList(counterLevelName);
        SpawningEngineUtil.spawnEntities();
    }*/

    //this modify is needed because of problems with refresh in the levels repository
    public static void initLevels() {
    	nLevelType0 = 2;
        nLevelType1 = 3;
        nLevelType2 = 3;
        counterLevelName = 9;
        initGameLevelList(counterLevelName);
        SpawningEngineUtil.spawnEntities();
    }

    private GameEngineUtil() { }

    private static void generateLevelsType0(final char var) {
        final LevelGeneratorImpl generator = new LevelGeneratorImpl();
        for (int i = 0; i < nLevelType0; i++) {
            if (i == 0) {
                generator.levelGenerator(var, counterLevelName, 1, false, false, false);

            } else if (i == nLevelType0 - 1) {
                generator.levelGenerator(var, counterLevelName, 1, true, false, true);

            } else {
                generator.levelGenerator(var, counterLevelName, 1, true, false, false);
              }
            counterLevelName++;
        }
    }

    private static void generateLevelsType1(final char var) {
        final LevelGeneratorImpl generator = new LevelGeneratorImpl();
        for (int i = 0; i < nLevelType1; i++) {
            if (i == 0) {
                generator.levelGenerator(var, counterLevelName, 1, true, true, false);

            } else if (i == nLevelType1 - 1) {
                generator.levelGenerator(var, counterLevelName, 1, true, false, true);

            } else {
                generator.levelGenerator(var, counterLevelName, 1, true, false, false);
            }
            counterLevelName++;
        }
    }

    private static void generateLevelsType2(final char var) {
        final LevelGeneratorImpl generator = new LevelGeneratorImpl();
        for (int i = 0; i < nLevelType2; i++) {
            if (i == 0) {
                generator.levelGenerator(var, counterLevelName, 1, true, true, false);

            } else if (i == nLevelType0 - 1) {
                generator.levelGenerator(var, counterLevelName, 1, true, false, false);

            } else {
                generator.levelGenerator(var, counterLevelName, 1, true, false, false);
            }
            counterLevelName++;
        }
    }



    private static void generateBossLevel(final char var) {
        final Path trace = Paths.get("src/assets/MapTraces/MapTraceBoss" + var + ".txt");
        final String path = "src/assets/text/levels/level" + counterLevelName + ".txt";
        final Path bossLevel = Paths.get(path);
        try {
            Files.copy(trace, bossLevel, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                }
        }

    private static void generateLevels() {
    	final Random rand = new Random();
    	int i = rand.nextInt(4);
    	char var;
    	if (i == 0) {
    		var = 'A';
    		}
    	else if (i == 1) {
    		var = 'K';
    		}
    	else {
    		var = 'T';
    		}
        generateLevelsType0(var);
        generateLevelsType1(var);
        generateLevelsType2(var);
        generateBossLevel(var);
    }

    private static void setNLevelsForType() {
        final LevelSettings variable = new LevelSettings();
        final Random rand = new Random();
        nLevelType0 = rand.nextInt(variable.getnMaxLevelsForType() - variable.getnMinLevelsForType()) + variable.getnMinLevelsForType();
        nLevelType1 = rand.nextInt(variable.getnMaxLevelsForType() - variable.getnMinLevelsForType()) + variable.getnMinLevelsForType();
        nLevelType2 = rand.nextInt(variable.getnMaxLevelsForType() - variable.getnMinLevelsForType()) + variable.getnMinLevelsForType();
        }

    /**
     * @return the nLevelType0
     */
    public static int getnLevelType0() {
        return nLevelType0;
    }

    /**
     * @return the nLevelType1
     */
    public static int getnLevelType1() {
        return nLevelType1;
    }

    /**
     * @return the nLevelType2
     */
    public static int getnLevelType2() {
        return nLevelType2;
    }

    /**
     * This method inserts the levels into the level list.
     * 
     * @param count
     *            the number of created levels
     */
    public static void initGameLevelList(final Integer count) {
        for (int i = 0; i < count; i++) {
            if (i < nLevelType0) {
                levelList.add(new GameLevel("levels/level" + i + ".txt", LevelDepth.ZERO)); 

            } else if ((i >= nLevelType0) && (i < (nLevelType0 + nLevelType1))) {
                levelList.add(new GameLevel("levels/level" + i + ".txt", LevelDepth.ONE));

            } else if ((i >= (nLevelType0 + nLevelType1)) && (i < (nLevelType0 + nLevelType1 + nLevelType2))) {
                levelList.add(new GameLevel("levels/level" + i + ".txt", LevelDepth.TWO));

            } else {
                levelList.add(new GameLevel("levels/level" + i + ".txt", LevelDepth.BOSS)); 
              }
            }
        setCurrentLevel(levelList.get(levelCounter));
        FXGL.getApp().getGameWorld().setLevel(currentLevel.get());
     }

    /**
     * This method is called whenever the player hits the exit door of one level.
     */
    public static void nextLevel() {
        Entity player = FXGL.getApp().getGameWorld().getEntitiesByType(GameEntityTypes.PLAYER).get(0);
        FXGL.getApp().getGameState().setValue("PlayerHealth", player.getComponent(HealthComponent.class).getHealth());
        FXGL.getApp().getGameScene().clearUINodes();
        levelCounter++;
        setCurrentLevel(levelList.get(levelCounter));
        FXGL.getApp().getGameWorld().setLevel(currentLevel.get());
        SpawningEngineUtil.spawnEntities();
    }

    /**
     * This method is used to set the current level variable.
     * 
     * @param level
     *             the level that is going to be set as current one
     */
    public static void setCurrentLevel(final GameLevel level) {
        currentLevel = level;
        }
}
