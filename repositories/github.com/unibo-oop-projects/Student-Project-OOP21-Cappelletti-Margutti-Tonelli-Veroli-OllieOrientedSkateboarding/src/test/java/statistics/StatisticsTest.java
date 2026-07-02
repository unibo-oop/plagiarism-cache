package statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import model.Model;
import model.ModelImpl;
import model.statistic.Statistics;
import sound.SoundFactoryImpl;

class StatisticsTest {

    private static final double SCREEN_WIDTH = 854.0;
    private static final double SCREEN_HEIGHT = 480.0;
    private static final double DIFFICULTY_FACTOR = 1.010;
    private static final double TOLERANCE = 1.010;
    private static final double MAX_DIFFICULTY = 15.0;

    private Statistics statistics;

    @BeforeEach
    void init() {
        // Initialize JavaFX environment
        final JFrame frame = new JFrame();
        final JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel);
        final Model model = new ModelImpl(SCREEN_WIDTH, SCREEN_HEIGHT, new SoundFactoryImpl());
        this.statistics = model.getStatistics();
    }

    @Test
    void testUpdate() {
        double difficulty = this.statistics.getDifficulty();
        final int distance = this.statistics.getDistance();
        this.statistics.update();
        assertEquals(difficulty * DIFFICULTY_FACTOR, this.statistics.getDifficulty());
        difficulty = difficulty * DIFFICULTY_FACTOR; 
        assertEquals(distance + difficulty, TOLERANCE, this.statistics.getDistance());
    }

    @Test
    void testMaxDifficulty() {
        double difficulty = this.statistics.getDifficulty();
        while (this.statistics.getDifficulty() <= MAX_DIFFICULTY) {
            this.statistics.update();
            difficulty = this.statistics.getDifficulty();
        }
        this.statistics.update();
        this.statistics.update();
        assertEquals(this.statistics.getDifficulty(), difficulty);
    }

    @Test
    void testCoinStatistics() {
        assertEquals(0, this.statistics.getGameCoins());
        this.statistics.increaseCoin(1);
        this.statistics.increaseCoin(1);
        assertEquals(2, this.statistics.getGameCoins());
    }

}
