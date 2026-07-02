package javaclimber.camera;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.camera.impl.AltitudeManager;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.physics.impl.Vector2dImpl;
import javaclimber.TestCostants;

/**
 * Tests for {@link AltitudeManager}.
 */
class AltitudeManagerTest {

    private static final double THRESHOLD_350 = 350.0;
    private static final double X_POS = 100.0;
    private static final double Y_POS_600 = 600.0;
    private static final double Y_POS_400 = 400.0;
    private static final double Y_POS_300 = 300.0;
    private static final double Y_POS_320 = 320.0;
    private static final double WIDTH = 50.0;
    private static final double HEIGHT = 50.0;
    private static final double TOTAL_DELTA_80 = 80.0;

    private Alien alien;
    private AltitudeManager altitudeManager;

    /**
     * Setup before each test.
     */
    @BeforeEach
    void setUp() {
        alien = new AlienImpl(new Vector2dImpl(X_POS, Y_POS_600),
                new Vector2dImpl(TestCostants.ZERO, TestCostants.ZERO),
                WIDTH,
                HEIGHT, null);
        altitudeManager = new AltitudeManager(alien, THRESHOLD_350);
    }

    /**
     * Verifies that when the alien is below the threshold, the observer is not
     * notified (delta remains 0).
     */
    @Test
    void testNoNotifyBelowThreshold() {
        final double[] result = {TestCostants.ZERO};
        altitudeManager.addObserver(delta -> result[0] = delta);
        alien.setPosition(new Vector2dImpl(X_POS, Y_POS_400));
        altitudeManager.verifiedAltitude();
        assertEquals(TestCostants.ZERO, result[0]);
    }

    /**
     * Verifies that when the alien moves above the threshold, the observer is
     * notified with the correct delta.
     */
    @Test
    void testNotifyExceedingThreshold() {
        final double[] result = {0.0};
        altitudeManager.addObserver(delta -> result[0] = delta);
        alien.setPosition(new Vector2dImpl(X_POS, Y_POS_300));
        altitudeManager.verifiedAltitude();
        assertEquals(TestCostants.TOTAL_DELTA_50, result[0]);
    }

    /**
     * Verifies that when the alien moves above the threshold multiple times, the
     * observer is notified with the correct cumulative delta.
     */
    @Test
    void testContinuousClimb() {
        final double[] totalDelta = {0.0};
        altitudeManager.addObserver(delta -> {
            totalDelta[0] += delta;
            alien.setPosition(new Vector2dImpl(alien.getPosX(), THRESHOLD_350));
        });

        alien.setPosition(new Vector2dImpl(X_POS, Y_POS_300));
        altitudeManager.verifiedAltitude();
        assertEquals(TestCostants.TOTAL_DELTA_50, totalDelta[0]);
        assertEquals(THRESHOLD_350, alien.getPosY());

        alien.setPosition(new Vector2dImpl(X_POS, Y_POS_320));
        altitudeManager.verifiedAltitude();
        assertEquals(TOTAL_DELTA_80, totalDelta[0]);
        assertEquals(THRESHOLD_350, alien.getPosY());
    }
}
