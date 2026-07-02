package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import org.junit.Test;

import zombieversity.model.score.Leaderboard;
import zombieversity.model.score.LeaderboardImpl;
import zombieversity.model.score.Score;
import zombieversity.model.score.ScoreImpl;

/**
 * Tests for Leaderboard and Score.
 */
public class ScoreTest {

    private static final int TIME_TO_WAIT = 3000;

    /**
     * Test setters and getters of Score.
     */
    @Test
    public void testScore() {
        final Score score = new ScoreImpl();

        assertTrue(Integer.valueOf(score.getKills()).equals(0));
        score.addKill();
        score.addKill();
        score.addKill();
        assertTrue(Integer.valueOf(score.getKills()).equals(3));

        assertTrue(score.getTimePlayed().equals("Not set"));
        score.setGameEnd();
        assertTrue(Integer.valueOf(score.getTimePlayed().compareTo("00:00:00")).equals(0));

        assertTrue(score.getNickname().equals("Not set"));
        score.setNickname("Test");
        assertTrue(score.getNickname().equals("Test"));


        final Score score2 = new ScoreImpl();

        try {
            Thread.sleep(TIME_TO_WAIT);
        } catch (InterruptedException e) {
            fail("Thread sleep error.");
        }

        score2.setGameEnd();
        assertTrue(Integer.valueOf(score2.getTimePlayed().compareTo("00:00:03")).equals(0));
    }

    /**
     * Test of the leaderboard. If already exists a leaderboard the number of assertions is reduced to not overwrite already saved data.
     */
    @Test
    public void testLeaderboard() {
        final String path = System.getProperty("user.home") + System.getProperty("file.separator") + ".zombieversity" + System.getProperty("file.separator") + "leaderboard.json";
        final File lbFile = new File(path);
        final Leaderboard lb = new LeaderboardImpl();
        final List<Score> list = lb.getLeaderboard();
        final Score score1 = new ScoreImpl();
        final Score score2 = new ScoreImpl();
        final int kills = 20;
        final int pos = 0;
        int size = 0;

        assertTrue(lbFile.exists());

        score1.setKills(kills);
        score2.setKills(kills * 2);
        score1.setNickname("Test Score One");
        score1.setNickname("Test Score Two");
        score1.setGameEnd();
        score2.setGameEnd();


        if (list.isEmpty()) {
            lb.handleScore(score1);
            lb.updateLeaderboard();
            size++;
            assertTrue(Integer.valueOf(lb.getLeaderboard().size()).equals(size));

            lb.handleScore(score2);
            lb.updateLeaderboard();
            size++;
            assertTrue(Integer.valueOf(lb.getLeaderboard().size()).equals(size));

            assertTrue(Integer.valueOf(lb.getLeaderboard().get(0).getKills()).equals(kills * 2));
            assertTrue(Integer.valueOf(lb.getLeaderboard().get(1).getKills()).equals(kills));
            assertTrue(Integer.valueOf(list.get(pos).getPosition()).equals(pos + 1));
            if (lbFile.delete()) {
                assertFalse(lbFile.exists());
            }
        } else if (list.size() > 1) {
            assertTrue(Integer.valueOf(list.get(pos).getPosition()).equals(pos + 1));
            assertTrue(list.get(pos).getKills() >= list.get(pos + 1).getKills());
            assertNotNull(list.get(pos).getNickname());
        }

    }

}
