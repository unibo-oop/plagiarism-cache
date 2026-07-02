package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.score.ScoreManagement;
import model.score.ScoreManagementImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Class to test if {@link ScoreManagement} works correctly.
 *
 */
public class ScoreManagementTest {

    private ScoreManagement scoreManagement;
    private Map<String, Integer> miniGameHistory;
    private String date;

    /**
     * Initialize test.
     */
    @BeforeEach
    public void init() {
        final Date d = new Date();
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        this.date = dateFormat.format(d);

        this.scoreManagement = new ScoreManagementImpl();
        this.miniGameHistory = new HashMap<>();
    }

    /**
     * Test Basic Behavior.
     */
    @Test
    public void testBasicBehavior() {
        this.init();
        this.scoreManagement.updateScore(24);
        this.scoreManagement.updateScore(42);
        this.scoreManagement.updateScore(23);
        this.scoreManagement.updateScore(31);
        assertEquals(25, this.scoreManagement.getAverage());
        assertEquals(42, this.scoreManagement.getBest());

        this.miniGameHistory.put(this.date, 30);
        this.scoreManagement.setMiniGameHistory(this.miniGameHistory);
        assertEquals(30, this.scoreManagement.getMiniGameHistory().get(this.date));
    }

    /**
     * Test check full history. 
     */
    @Test
    public void testCheckFullHistory() {
        this.init();

        this.miniGameHistory.put("01/04/20", 24);
        this.miniGameHistory.put("02/04/20", 20);
        this.miniGameHistory.put("03/04/20", 19);
        this.miniGameHistory.put("04/04/20", 50);
        this.miniGameHistory.put("05/04/20", 24);
        this.miniGameHistory.put("06/04/20", 24);
        this.miniGameHistory.put("01/12/19", 24);
        this.miniGameHistory.put("15/04/20", 24);
        this.miniGameHistory.put("26/04/20", 24);
        this.miniGameHistory.put("21/11/19", 24);

        this.scoreManagement.setMiniGameHistory(this.miniGameHistory);
        this.scoreManagement.updateScore(51);
        assertFalse(this.scoreManagement.getMiniGameHistory().containsKey("21/11/19"));
        assertEquals(7, this.scoreManagement.getMiniGameHistory().size());
    }
}
