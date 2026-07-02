package it.unibo;

import it.unibo.model.CannonModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;;

/**
 * Unit tests for the {@link CannonModel} class.
 * These tests check the functionality of the cannon, including movement and aiming.
 */
class CannonModelTest {
    private CannonModel cannon;

    /**
     * Initializes the test environment before each test method is executed.
     * A new CannonModel instance is created for each test.
     */
    @BeforeEach
    void setUp() {
        cannon = new CannonModel();
    }

    /**
     * Tests the initialization of the cannon.
     * Verifies that the cannon starts at the correct default position and angle.
     */
    @Test
    void testCannonInitialization() {
        assertEquals(0.5, cannon.getX(), "The cannon should start at position 0.5.");
        assertEquals(0.5, cannon.getAngle(), "The cannon should start at angle 0.5.");
    }

    /**
     * Tests moving the cannon left.
     * Verifies that the position decreases correctly and does not go below 0.
     */
    @Test
    void testMoveLeft() {
        cannon.moveLeft();
        assertEquals(0.48, cannon.getX(), "The cannon should move left by 0.02.");
        
        for (int i = 0; i < 50; i++) {
            cannon.moveLeft();
        }
        assertEquals(0, cannon.getX(), "The cannon should not move beyond the left boundary (0).");
    }

    /**
     * Tests moving the cannon right.
     * Verifies that the position increases correctly and does not go above 1.
     */
    @Test
    void testMoveRight() {
        cannon.moveRight();
        assertEquals(0.52, cannon.getX(), "The cannon should move right by 0.02.");
        
        for (int i = 0; i < 50; i++) {
            cannon.moveRight();
        }
        assertEquals(1, cannon.getX(), "The cannon should not move beyond the right boundary (1).");
    }

    /**
     * Tests aiming the cannon up.
     * Verifies that the angle increases correctly and does not go above 1.
     */
    @Test
    void testAimUp() {
        cannon.aimUp();
        assertEquals(0.52, cannon.getAngle(), "The cannon should aim up by 0.02.");
        
        for (int i = 0; i < 50; i++) {
            cannon.aimUp();
        }
        assertEquals(1, cannon.getAngle(), "The cannon should not aim beyond the maximum angle (1).");
    }

    /**
     * Tests aiming the cannon down.
     * Verifies that the angle decreases correctly and does not go below 0.
     */
    @Test
    void testAimDown() {
        cannon.aimDown();
        assertEquals(0.48, cannon.getAngle(), "The cannon should aim down by 0.02.");
        
        for (int i = 0; i < 50; i++) {
            cannon.aimDown();
        }
        assertEquals(0, cannon.getAngle(), "The cannon should not aim below the minimum angle (0).");
    }
}
