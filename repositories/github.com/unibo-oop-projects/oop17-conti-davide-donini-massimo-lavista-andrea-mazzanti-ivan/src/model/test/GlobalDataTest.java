package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import model.data.Achievement;
import model.data.AchievementType;
import model.data.GameData;
import model.data.GameDataImpl;
import model.data.GlobalData;
import model.data.GlobalDataImpl;
import model.data.HighScore;
import model.data.HighScoreImpl;
import model.entities.powerup.PowerUpType;

/**
 * Test for global data.
 */
public class GlobalDataTest {

    private final GlobalData globalData = new GlobalDataImpl();
    private static final int TIME_PLAYED_IN_MS = 120000;
    private static final int TIME_PLAYED_IN_MINUTE = 2;
    private static final String ACHIEVEMENTS = "Achievements error";
    private static final String HIGH_SCORES = "High scores error";

    private GameData generateGameData() {
        final GameData gameData = new GameDataImpl();
        gameData.addScore(1000);
        gameData.addTime(TIME_PLAYED_IN_MS);
        gameData.increasePowerUpByType(PowerUpType.DAMAGE);
        gameData.increasePowerUpByType(PowerUpType.RATE_OF_FIRE);
        gameData.increaseNumDeadEemies();
        gameData.increaseNumDeadEemies();
        gameData.increaseNumDeadEemies();
        gameData.increaseNumDeadEemies();
        gameData.setLife(1);
        gameData.setLife(2);
        gameData.increasePowerUpByType(PowerUpType.LIFE);
        gameData.turnOnShield();
        gameData.turnOnShield();
        return gameData;
    }

    /**
     * Basic test game data.
     */
    @Test
    public final void testAddingGameData() {
        final GameData gameData = generateGameData();
        final Optional<String> pippo = Optional.of("pippo");
        final Optional<String> pluto = Optional.of("pluto");
        this.globalData.addGameData(gameData, pippo);
        assertEquals(ACHIEVEMENTS, globalData.getAchievements().get(AchievementType.GAMES_PLAYED).getPlayerValue(), 1);
        assertNotEquals(ACHIEVEMENTS, globalData.getAchievements().get(AchievementType.NUM_DEAD_ENEMIES).getPlayerValue(), 3);
        assertEquals(ACHIEVEMENTS, globalData.getAchievements().get(AchievementType.TIME_PLAYED).getPlayerValue(), TIME_PLAYED_IN_MINUTE);
        assertEquals(ACHIEVEMENTS, globalData.getAchievements().get(AchievementType.NUM_POWER_UP).getPlayerValue(), 3);
        assertFalse(HIGH_SCORES, this.globalData.getHighScores().contains(new HighScoreImpl("pippo", 100)));
        this.globalData.addGameData(gameData, pluto);
        assertNotEquals(ACHIEVEMENTS, globalData.getAchievements().get(AchievementType.GAMES_PLAYED).getPlayerValue(), 1);
        assertEquals(ACHIEVEMENTS, globalData.getAchievements().get(AchievementType.NUM_DEAD_ENEMIES).getPlayerValue(), 8);
        assertNotEquals(ACHIEVEMENTS, globalData.getAchievements().get(AchievementType.TIME_PLAYED).getPlayerValue(), TIME_PLAYED_IN_MINUTE);
        assertNotEquals(ACHIEVEMENTS, globalData.getAchievements().get(AchievementType.NUM_POWER_UP).getPlayerValue(), 4);
        assertTrue(HIGH_SCORES, this.globalData.getHighScores().contains(new HighScoreImpl("pluto", 1000)));
    }

    /**
     * Test unmodifiable lists.
     */
    @Test
    public final void testUnmodifiableLists() {
        final GameData gameData = generateGameData();
        final Optional<String> paperino = Optional.of("paperino");
        globalData.addGameData(gameData, paperino);
        final List<HighScore> hs = this.globalData.getHighScores();
        final Map<AchievementType, Achievement> a = this.globalData.getAchievements();
        assertEquals(HIGH_SCORES, this.globalData.getHighScores(), hs);
        assertEquals(ACHIEVEMENTS, this.globalData.getAchievements(), a);
        globalData.addGameData(gameData, paperino);
        assertNotEquals(this.globalData.getHighScores(), hs);
        assertNotEquals(this.globalData.getAchievements(), a);
    }

}
