package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.PointsComponent;
import it.unibo.utilities.Constants;

import java.io.IOException;

/**
 * Test class for {@link PointsComponent}.
 */
final class PointsComponentTest {

    /**
     * The PointsComponent instance to be tested.
     */
    private PointsComponent pointsComponent;

    private static final int INITIAL_POINTS = 0;
    private static final int POINTS_TO_ADD_FIRST = 10;
    private static final int POINTS_TO_ADD_SECOND = 5;
    private static final int EXPECTED_POINTS_AFTER_FIRST_ADD = 10;
    private static final int EXPECTED_POINTS_AFTER_SECOND_ADD = 15;

    /**
     * Sets up the test environment before each test.
     * 
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings("static-access")
    @BeforeEach
    void setUp() throws IOException {
        pointsComponent = new PointsComponent();
        pointsComponent.resetHighScoreOnFirstLaunch();
    }

    /**
     * Tests the readFromFile method.
     */
    @Test
    void testReadFromFile() {
        pointsComponent.readFromFile();
        assertEquals(INITIAL_POINTS, pointsComponent.getHighScore(), "High score should be read as 0 from file");
    }

    /**
     * Tests the getPoints method.
     */
    @Test
    void testGetPoints() {
        assertEquals(INITIAL_POINTS, pointsComponent.getPoints(), "Initial points should be 0");
    }

    /**
     * Tests the getHighScore method.
     */
    @Test
    void testGetHighScore() {
        assertEquals(INITIAL_POINTS, pointsComponent.getHighScore(), "Initial high score should be 0");
    }

    /**
     * Tests the addPoints method.
     */
    @Test
    void testAddPoints() {
        pointsComponent.addPoints(POINTS_TO_ADD_FIRST);
        assertEquals(EXPECTED_POINTS_AFTER_FIRST_ADD, pointsComponent.getPoints(),
                "Points should be 10 after adding 10 points");
        assertEquals(EXPECTED_POINTS_AFTER_FIRST_ADD, pointsComponent.getHighScore(),
                "High score should be updated to 10 after adding 10 points");
        pointsComponent.addPoints(POINTS_TO_ADD_SECOND);
        assertEquals(EXPECTED_POINTS_AFTER_SECOND_ADD, pointsComponent.getPoints(),
                "Points should be 15 after adding 5 more points");
        assertEquals(EXPECTED_POINTS_AFTER_SECOND_ADD, pointsComponent.getHighScore(),
                "High score should be updated to 15 after adding 5 more points");
    }

    /**
     * Tests the writeToFile method.
     * 
     * @throws IOException if an I/O error occurs
     */
    @Test
    void testWriteToFile() throws IOException {
        pointsComponent.writeToFile(Constants.Felix.FIXED_WINDOW_POINTS);
        pointsComponent.readFromFile();
        assertEquals(Constants.Felix.FIXED_WINDOW_POINTS, pointsComponent.getHighScore(),
                "High score should be written as 50 to the file");
    }

    /**
     * Tests the getComponent method.
     */
    @Test
    void testGetComponent() {
        assertEquals(ComponentType.POINTS, pointsComponent.getComponent());
    }
}
