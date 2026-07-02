package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import controller.LeaderboardManagerImpl;
import utilities.Pair;
import controller.LeaderboardManager;

/**
 * JUnit test for leaderboard manager.
 */
public class LeaderboardTest {

    /**
     * test for addScore method.
     */
    @Test
    public void testAddScore() {
        final LeaderboardManager lm = new LeaderboardManagerImpl();
        assertTrue(lm.getScoreList().isEmpty());
        //add a score
        Pair<String, Integer> newScore = new Pair<>("Test", 100);
        lm.addScore(newScore);
        assertFalse(lm.getScoreList().isEmpty());
        assertEquals(lm.getScoreList().get(0).getX(), newScore.getX());
        assertEquals(lm.getScoreList().get(0).getY(), newScore.getY());
    }
    /**
     * test for resetScore.
     */
    @Test
    public void testResetScore() {
        final LeaderboardManager lm = new LeaderboardManagerImpl();
        lm.addScore(new Pair<String, Integer>("Test", 100));
        assertFalse(lm.getScoreList().isEmpty());
        lm.resetAllScore();
        assertTrue(lm.getScoreList().isEmpty());
    }
    /**
     * test leaderboard order.
     */
    @Test
    public void testLeaderboardOrder() {
        final LeaderboardManager lm = new LeaderboardManagerImpl();
        final Pair<String, Integer> score1 = new Pair<>("S1", 100);
        final Pair<String, Integer> score2 = new Pair<>("S2", 75);
        final Pair<String, Integer> score3 = new Pair<>("S3", 50);
        final Pair<String, Integer> score4 = new Pair<>("S4", 25);
        //Score added not in order
        lm.addScore(score4);
        lm.addScore(score2);
        lm.addScore(score3);
        lm.addScore(score1);
        //lm.getScoreList().forEach(s -> System.out.println(s.getX() + " - " + s.getY()));
        assertEquals(lm.getScoreList().get(0).getX(), score1.getX());
        assertEquals(lm.getScoreList().get(0).getY(), score1.getY());
        assertEquals(lm.getScoreList().get(1).getX(), score2.getX());
        assertEquals(lm.getScoreList().get(1).getY(), score2.getY());
        assertEquals(lm.getScoreList().get(2).getX(), score3.getX());
        assertEquals(lm.getScoreList().get(2).getY(), score3.getY());
        assertEquals(lm.getScoreList().get(3).getX(), score4.getX());
        assertEquals(lm.getScoreList().get(3).getY(), score4.getY());
    }

    @Test
    public void testWriteFile() {
        final LeaderboardManager lm = new LeaderboardManagerImpl();
        final Pair<String, Integer> score1 = new Pair<>("S1", 100);
        final Pair<String, Integer> score2 = new Pair<>("S2", 75);
        final Pair<String, Integer> score3 = new Pair<>("S3", 50);
        final Pair<String, Integer> score4 = new Pair<>("S4", 25);
        lm.addScore(score4);
        lm.addScore(score2);
        lm.addScore(score3);
        lm.addScore(score1);
        lm.updateFile();
        File file = new File("leaderboard.dat");
        assertTrue(file.exists());
        assertTrue(file.delete());
    }

    @Test
    public void testReadFile() { 
        final LeaderboardManager lm = new LeaderboardManagerImpl();
        final Pair<String, Integer> score1 = new Pair<>("S1", 100);
        final Pair<String, Integer> score2 = new Pair<>("S2", 75);
        final Pair<String, Integer> score3 = new Pair<>("S3", 50);
        lm.addScore(score1);
        lm.addScore(score2);
        lm.addScore(score3);
        lm.updateFile();
        File file = new File("leaderboard.dat");
        assertTrue(file.exists());
        final LeaderboardManager lm2 = new LeaderboardManagerImpl();
        assertEquals(lm.getScoreList().get(0).getX(), lm2.getScoreList().get(0).getX());
        assertEquals(lm.getScoreList().get(0).getY(), lm2.getScoreList().get(0).getY());
        assertEquals(lm.getScoreList().get(1).getX(), lm2.getScoreList().get(1).getX());
        assertEquals(lm.getScoreList().get(1).getY(), lm2.getScoreList().get(1).getY());
        assertEquals(lm.getScoreList().get(2).getX(), lm2.getScoreList().get(2).getX());
        assertEquals(lm.getScoreList().get(2).getY(), lm2.getScoreList().get(2).getY());
        assertTrue(file.delete());
    }
}
