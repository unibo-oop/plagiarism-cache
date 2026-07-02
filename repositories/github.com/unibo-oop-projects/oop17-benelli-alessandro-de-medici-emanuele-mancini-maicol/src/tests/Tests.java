package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import controller.LevelManagerImpl;
import controller.ScoreImpl;
import controller.player.PlayerPathImpl;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import model.obstacle.ObstacleImpl;
import model.player.PlayerImpl;
import utilities.Utilities;

/**
 * Class that runs all JUnit tests.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class Tests {

    /**
     * Launches JFXPanel before running tests.
     */
    @Before
    public void setUp() {
        new JFXPanel();
    }

    /**
     * Closes the application after running tests.
     */
    @After
    public void tearDown() {
        Platform.exit();
    }

    /**
     * Runs JUnit tests.
     */
    @Test
    public void resultTest() {
        // Start Score Timer
        ScoreImpl.get().startScore();

        // Test LevelOne
        ObstacleImpl.get().clearObstaclePositions();
        LevelManagerImpl.get().resetLevelNumber();
        LevelManagerImpl.get().resetLevel();
        assertEquals("Level number is not the first one", LevelManagerImpl.get().getLevelNumber(),
                Utilities.FIRST_LEVEL);
        // Test background path
        assertEquals("LevelOne's background path doesn't match",
                LevelManagerImpl.get().chooseLevel().getBackgroundPath(), "/levelOne/levelOne.png");
        // Test step sound path
        assertEquals("LevelOne's step sound path doesn't match",
                LevelManagerImpl.get().chooseLevel().getStepSoundPath(), "/songs/stepSound.mp3");
        // Test music path
        assertEquals("LevelOne's music path doesn't match", LevelManagerImpl.get().chooseLevel().getMusicPath(),
                "/songs/levelOneMusic.mp3");
        // Test number of obstacles
        assertEquals("LevelOne's obstacles number doesn't match", ObstacleImpl.get().getObstaclePositions().size(), 6);
        // Test map bounds
        assertEquals("LevelOne's mapBoundMinX doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMinX(),
                8);
        assertEquals("LevelOne's mapBoundMaxX doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMaxX(),
                12);
        assertEquals("LevelOne's mapBoundMinY doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMinY(),
                10);
        assertEquals("LevelOne's mapBoundMaxY doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMaxY(),
                14);
        // Test character's start and finish tiles
        assertEquals("LevelOne's player's starting column doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharStartCol(), 10);
        assertEquals("LevelOne's player's starting row doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharStartRow(), 16);
        assertEquals("LevelOne's player's ending column doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharFinishCol(), 10);
        assertEquals("LevelOne's player's ending row doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharFinishRow(), 8);
        // Test playableMap List
        assertFalse("LevelOne's playableMap is empty", LevelManagerImpl.get().chooseLevel().getPlayableMap().isEmpty());
        // Test playerPositions List
        assertTrue("LevelOne's playerPositions isn't empty", PlayerPathImpl.get().getPlayerPath().isEmpty());

        // Test LevelTwo
        ObstacleImpl.get().clearObstaclePositions();
        LevelManagerImpl.get().setLevelNumber(2);
        LevelManagerImpl.get().resetLevel();
        assertEquals("Level number is not the second one", LevelManagerImpl.get().getLevelNumber(), 2);
        // Test background path
        assertEquals("LevelTwo's background path doesn't match",
                LevelManagerImpl.get().chooseLevel().getBackgroundPath(), "/levelTwo/levelTwo.png");
        // Test step sound path
        assertEquals("LevelTwo's step sound path doesn't match",
                LevelManagerImpl.get().chooseLevel().getStepSoundPath(), "/songs/stepGrass.mp3");
        // Test music path
        assertEquals("LevelTwo's music path doesn't match", LevelManagerImpl.get().chooseLevel().getMusicPath(),
                "/songs/levelTwoMusic.mp3");
        // Test number of obstacles
        assertEquals("LevelTwo's obstacles number doesn't match", ObstacleImpl.get().getObstaclePositions().size(), 6);
        // Test map bounds
        assertEquals("LevelTwo's mapBoundMinX doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMinX(),
                6);
        assertEquals("LevelTwo's mapBoundMaxX doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMaxX(),
                14);
        assertEquals("LevelTwo's mapBoundMinY doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMinY(),
                8);
        assertEquals("LevelTwo's mapBoundMaxY doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMaxY(),
                11);
        // Test character's start and finish tiles
        assertEquals("LevelTwo's player's starting column doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharStartCol(), 10);
        assertEquals("LevelTwo's player's starting row doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharStartRow(), 13);
        assertEquals("LevelTwo's player's ending column doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharFinishCol(), 10);
        assertEquals("LevelTwo's player's ending row doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharFinishRow(), 6);
        // Test playableMap List
        assertFalse("LevelTwo's playableMap is empty", LevelManagerImpl.get().chooseLevel().getPlayableMap().isEmpty());
        // Test playerPositions List
        assertTrue("LevelTwo's playerPositions isn't empty", PlayerPathImpl.get().getPlayerPath().isEmpty());

        // Test LevelThree
        ObstacleImpl.get().clearObstaclePositions();
        LevelManagerImpl.get().setLevelNumber(3);
        LevelManagerImpl.get().resetLevel();
        assertEquals("Level number is not the third one", LevelManagerImpl.get().getLevelNumber(), 3);
        // Test background path
        assertEquals("LevelThree's background path doesn't match",
                LevelManagerImpl.get().chooseLevel().getBackgroundPath(), "/levelThree/levelThree.png");
        // Test step sound path
        assertEquals("LevelThree's step sound path doesn't match",
                LevelManagerImpl.get().chooseLevel().getStepSoundPath(), "/songs/stepSnow.mp3");
        // Test music path
        assertEquals("LevelThree's music path doesn't match", LevelManagerImpl.get().chooseLevel().getMusicPath(),
                "/songs/levelThreeMusic.mp3");
        // Test number of obstacles
        assertEquals("LevelThree's obstacles number doesn't match", ObstacleImpl.get().getObstaclePositions().size(),
                6);
        // Test map bounds
        assertEquals("LevelThree's mapBoundMinX doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMinX(),
                5);
        assertEquals("LevelThree's mapBoundMaxX doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMaxX(),
                15);
        assertEquals("LevelThree's mapBoundMinY doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMinY(),
                8);
        assertEquals("LevelThree's mapBoundMaxY doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMaxY(),
                11);
        // Test character's start and finish tiles
        assertEquals("LevelThree's player's starting column doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharStartCol(), 10);
        assertEquals("LevelThree's player's starting row doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharStartRow(), 13);
        assertEquals("LevelThree's player's ending column doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharFinishCol(), 10);
        assertEquals("LevelThree's player's ending row doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharFinishRow(), 6);
        // Test playableMap List
        assertFalse("LevelThree's playableMap is empty",
                LevelManagerImpl.get().chooseLevel().getPlayableMap().isEmpty());
        // Test playerPositions List
        assertTrue("LevelThree's playerPositions isn't empty", PlayerPathImpl.get().getPlayerPath().isEmpty());

        // Test LevelFour
        ObstacleImpl.get().clearObstaclePositions();
        LevelManagerImpl.get().setLevelNumber(4);
        LevelManagerImpl.get().resetLevel();
        assertEquals("Level number is not the fourth one", LevelManagerImpl.get().getLevelNumber(), 4);
        // Test background path
        assertEquals("LevelFour's background path doesn't match",
                LevelManagerImpl.get().chooseLevel().getBackgroundPath(), "/levelFour/levelFour.png");
        // Test step sound path
        assertEquals("LevelFour's step sound path doesn't match",
                LevelManagerImpl.get().chooseLevel().getStepSoundPath(), "/songs/stepSound.mp3");
        // Test music path
        assertEquals("LevelFour's music path doesn't match", LevelManagerImpl.get().chooseLevel().getMusicPath(),
                "/songs/levelFourMusic.mp3");
        // Test number of obstacles
        assertEquals("LevelFour's obstacles number doesn't match", ObstacleImpl.get().getObstaclePositions().size(), 8);
        // Test map bounds
        assertEquals("LevelFour's mapBoundMinX doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMinX(),
                5);
        assertEquals("LevelFour's mapBoundMaxX doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMaxX(),
                15);
        assertEquals("LevelFour's mapBoundMinY doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMinY(),
                11);
        assertEquals("LevelFour's mapBoundMaxY doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMaxY(),
                15);
        // Test character's start and finish tiles
        assertEquals("LevelFour's player's starting column doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharStartCol(), 10);
        assertEquals("LevelFour's player's starting row doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharStartRow(), 15);
        assertEquals("LevelFour's player's ending column doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharFinishCol(), 10);
        assertEquals("LevelFour's player's ending row doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharFinishRow(), 9);
        // Test playableMap List
        assertFalse("LevelFour's playableMap is empty",
                LevelManagerImpl.get().chooseLevel().getPlayableMap().isEmpty());
        // Test playerPositions List
        assertTrue("LevelFour's playerPositions isn't empty", PlayerPathImpl.get().getPlayerPath().isEmpty());

        // Test LevelFive
        ObstacleImpl.get().clearObstaclePositions();
        LevelManagerImpl.get().setLevelNumber(Utilities.TOTAL_LEVELS);
        LevelManagerImpl.get().resetLevel();
        assertEquals("Level number is not the fifth one", LevelManagerImpl.get().getLevelNumber(),
                Utilities.TOTAL_LEVELS);
        // Test background path
        assertEquals("LevelFive's background path doesn't match",
                LevelManagerImpl.get().chooseLevel().getBackgroundPath(), "/levelFive/levelFive.png");
        // Test step sound path
        assertEquals("LevelFive's step sound path doesn't match",
                LevelManagerImpl.get().chooseLevel().getStepSoundPath(), "/songs/stepSound.mp3");
        // Test music path
        assertEquals("LevelFive's music path doesn't match", LevelManagerImpl.get().chooseLevel().getMusicPath(),
                "/songs/levelFiveMusic.mp3");
        // Test number of obstacles
        assertEquals("LevelFive's obstacles number doesn't match", ObstacleImpl.get().getObstaclePositions().size(),
                18);
        // Test map bounds
        assertEquals("LevelFive's mapBoundMinX doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMinX(),
                4);
        assertEquals("LevelFive's mapBoundMaxX doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMaxX(),
                16);
        assertEquals("LevelFive's mapBoundMinY doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMinY(),
                9);
        assertEquals("LevelFive's mapBoundMaxY doesn't match", LevelManagerImpl.get().chooseLevel().getMapBoundMaxY(),
                14);
        // Test character's start and finish tiles
        assertEquals("LevelFive's player's starting column doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharStartCol(), 10);
        assertEquals("LevelFive's player's starting row doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharStartRow(), 15);
        assertEquals("LevelFive's player's ending column doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharFinishCol(), 10);
        assertEquals("LevelFive's player's ending row doesn't match",
                LevelManagerImpl.get().chooseLevel().getCharFinishRow(), 7);
        // Test playableMap List
        assertFalse("LevelFive's playableMap is empty",
                LevelManagerImpl.get().chooseLevel().getPlayableMap().isEmpty());
        // Test playerPositions List
        assertTrue("LevelFive's playerPositions isn't empty", PlayerPathImpl.get().getPlayerPath().isEmpty());

        // Test player position in the grid
        PlayerImpl.get().setPlayerPosition(3, 5);
        assertTrue("Player position not set or get correctly",
                PlayerImpl.get().getPlayerPosition().equals(new Point(3, 5)));
        // Test obstacles' collision
        assertTrue("Obstacle's collision not detected", PlayerPathImpl.get().checkObstacle(new Point(5, 10)));
        // Test map bounds' collision
        assertTrue("Map bound's collision not detected", PlayerPathImpl.get().checkBound(4, 8));

        // Test Score Timer
        ScoreImpl.get().stopTimer();
        assertTrue("Score Timer issues", ScoreImpl.get().getTime() > 0);
    }
}
