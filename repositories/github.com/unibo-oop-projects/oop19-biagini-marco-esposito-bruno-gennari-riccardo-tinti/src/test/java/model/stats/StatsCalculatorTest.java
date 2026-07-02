package model.stats;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import model.enums.StatsInfo;
import model.players.HumanPlayer;
import model.players.Player;

/**
 * JUnit test for {@link Statistics}.
 */
public class StatsCalculatorTest {

    private static final Double WIN_LOSS_SCORE = 20.0;
    private static final Double TOTALS_EXPECTED = 1.0;
    private static final Double WINS_EXPECTED = 1.0;
    private static final Double WINSRATE_EXPECTED = 100.0;
    private static final Double LOSS_EXPECTED = 1.0;
    private static final Double LOSSRATE_EXPECTED = 100.0;

    private Statistics stats;

    /**
     * Test if the calculator of the winner statistics works correctly.
     */
    @Test 
    void basicWinStatsTest() {
        Player player1 = new HumanPlayer("Test1", "PassTest1");
        Player player2 = new HumanPlayer("Test2", "PassTest2");

        stats = new WinnerStatsCalculator(player1, WIN_LOSS_SCORE);
        stats.basicStats();
        Map<String, Double> res = player1.getStatistics();

        Map<String, Double> expected = new HashMap<String, Double>(player2.getStatistics());
        expected.computeIfPresent(StatsInfo.RECORD.getName(), (x, y) -> Double.valueOf(WIN_LOSS_SCORE));
        expected.computeIfPresent(StatsInfo.TOTALS.getName(), (x, y) -> Double.valueOf(TOTALS_EXPECTED));
        expected.computeIfPresent(StatsInfo.WINS.getName(), (x, y) -> Double.valueOf(WINS_EXPECTED));
        expected.computeIfPresent(StatsInfo.WINS_RATE.getName(), (x, y) -> Double.valueOf(WINSRATE_EXPECTED));

        assertEquals(expected, res);
    }

    /**
     * Test if the calculator of the loser statistics works correctly.
     */
    @Test 
    void basicLossStatsTest() {
        Player player1 = new HumanPlayer("Test1", "PassTest1");
        Player player2 = new HumanPlayer("Test2", "PassTest2");

        stats = new LoserStatsCalculator(player1, WIN_LOSS_SCORE);
        stats.basicStats();
        Map<String, Double> res = player1.getStatistics();

        Map<String, Double> expected = new HashMap<String, Double>(player2.getStatistics());
        expected.computeIfPresent(StatsInfo.RECORD.getName(), (x, y) -> Double.valueOf(WIN_LOSS_SCORE));
        expected.computeIfPresent(StatsInfo.TOTALS.getName(), (x, y) -> Double.valueOf(TOTALS_EXPECTED));
        expected.computeIfPresent(StatsInfo.LOSS.getName(), (x, y) -> Double.valueOf(LOSS_EXPECTED));
        expected.computeIfPresent(StatsInfo.LOSS_RATE.getName(), (x, y) -> Double.valueOf(LOSSRATE_EXPECTED));

        assertEquals(expected, res);
    }

}
