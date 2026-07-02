package utilities.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.players.Player;
import model.players.ranking.Ranking;
import model.players.ranking.RankingManager;
import utilities.Colors;

/**
 * Class used to test the ranking manager.
 * Andrea Serafini.
 *
 */
public class RankingTest {

    private final Ranking score = new RankingManager();
    private static final int FIRST = 6;
    private static final int SECOND = 10;
    private static final int THIRD = 12;
    private static final int FOURTH = 15;
    private static final int FIFTH = 20;
    private static final int OUT = 50;


    private void clear() {
        this.score.clear();
    }

    private void add() {
        score.addScore(new Player("Andrea", Colors.Blue, FIRST));
        score.addScore(new Player("Piero", Colors.Red, SECOND));
    }

    private void addPlayer(final String name, final int turn) {
        score.addScore(new Player(name, Colors.Blue, turn));
    }

    /**
     *
     */
    @Test
    public void clearTest() {
        assertTrue("Is present", this.score.isPresent());
        this.add();
        System.out.println(score.getHighscoreString());
        this.addPlayer("GiulioYES", FOURTH);
        this.addPlayer("GiulioNO", OUT);
        this.addPlayer("GiulioYES", FIFTH);
        this.addPlayer("GiulioYES", THIRD);
        System.out.println(score.getHighscoreString());
        this.clear();
        assertFalse("Is not present", this.score.isPresent());
    }
}
