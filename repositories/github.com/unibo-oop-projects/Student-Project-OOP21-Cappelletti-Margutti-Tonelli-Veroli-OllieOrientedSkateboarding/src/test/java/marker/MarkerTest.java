package marker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import model.Model;
import model.ModelImpl;
import model.marker.MarkerFactory;
import model.marker.MarkerFactoryImpl;
import model.marker.MarkerManager;
import model.marker.MarkerManagerImpl;
import model.statistic.Statistics;
import sound.SoundFactoryImpl;

class MarkerTest {

    private static final double SCREEN_WIDTH = 854.0;
    private static final double SCREEN_HEIGHT = 480.0;
    private static final int LAST_DEATH_DISTANCE = 53;
    private static final int RECORD_DISTANCE = 405;
    private static final int DISTANCE_BETWEEN_MARKERS = 10;
    private static final int BIG_DISTANCE = 100_000;
    private static final int NUMBER_OF_MARKERS_LAST_DEATH_TEST = (LAST_DEATH_DISTANCE * 2) / DISTANCE_BETWEEN_MARKERS + 1;
    private static final int NUMBER_OF_MARKERS_RECORD_TEST = (RECORD_DISTANCE * 2) / DISTANCE_BETWEEN_MARKERS + 1;

    private Statistics statistics;
    private MarkerManager markerManager;

    @BeforeEach
    public void init() {
        // Initialize JavaFX environment
        final JFrame frame = new JFrame();
        final JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel);
        final Model model = new ModelImpl(SCREEN_WIDTH, SCREEN_HEIGHT, new SoundFactoryImpl());
        this.markerManager = new MarkerManagerImpl(LAST_DEATH_DISTANCE, RECORD_DISTANCE);
        this.statistics = model.getStatistics();
    }

    @Test
    void testInitialState() {
        assertTrue(this.markerManager.getMarkers().isEmpty());
    }

    @Test
    void testCommonMarker() {
        final MarkerFactory factory = new MarkerFactoryImpl();
        int distance = 0;
        String markerString = "";
        while (distance <= DISTANCE_BETWEEN_MARKERS) {
            if (this.markerManager.isCommonMarkerToBeCreated(distance)) {
                // Creates a common marker
                this.markerManager.getMarkers()
                    .add(Optional.of(factory.createCommonMarker(Integer.toString(DISTANCE_BETWEEN_MARKERS))));
                // Saves the string distance of the common marker
                markerString = Integer.toString(distance);
            }
            this.statistics.update();
            distance = this.statistics.getDistance();
        }
        assertEquals(1, this.markerManager.getMarkers().size());
        assertEquals(markerString, this.markerManager.getMarkers().get(0).get().getText());
        // Moves markers out of the screen
        this.markerManager.update(BIG_DISTANCE);
        assertTrue(this.markerManager.getMarkers().isEmpty());
    }

    @Test
    void testLastDeathMarker() {
        int distance = 0;
        while (distance <= LAST_DEATH_DISTANCE * 2) {
            this.markerManager.checkCreateMarker(distance);
            this.statistics.update();
            distance = this.statistics.getDistance();
        }
        assertEquals(NUMBER_OF_MARKERS_LAST_DEATH_TEST, this.markerManager.getMarkers().size());
        // Moves markers out of the screen
        this.markerManager.update(BIG_DISTANCE);
        assertTrue(this.markerManager.getMarkers().isEmpty());
    }

    @Test
    void testRecordMarker() {
        int distance = 0;
        while (distance <= RECORD_DISTANCE * 2) {
            this.markerManager.checkCreateMarker(distance);
            this.statistics.update();
            distance = this.statistics.getDistance();
        }
        assertEquals(NUMBER_OF_MARKERS_RECORD_TEST, this.markerManager.getMarkers().size());
        // Moves markers out of the screen
        this.markerManager.update(BIG_DISTANCE);
        assertTrue(this.markerManager.getMarkers().isEmpty());
    }

}
