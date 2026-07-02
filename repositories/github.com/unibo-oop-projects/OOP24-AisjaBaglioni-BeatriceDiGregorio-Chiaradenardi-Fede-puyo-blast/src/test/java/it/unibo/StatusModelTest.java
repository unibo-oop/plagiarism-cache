package it.unibo;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.StatusModel;

/**
 * Test class for {@link StatusModel}.
 * This class verifies the correct behavior of the game status tracking logic.
 */
public class StatusModelTest {
    
    private StatusModel statusModel;

    /**
     * Sets up a new instance of {@code StatusModel} before each test.
     */
    @BeforeEach
    public void setUp() {
        statusModel = new StatusModel();
    }

    /**
     * Tests that a new game starts in the expected initial state (not ended, no stars set).
     */
    @Test
    public void testInitialStatus() {
        assertFalse(statusModel.isGameEnded(), "Game should not be ended initially.");
        assertTrue(statusModel.getEndStars().isEmpty(), "Stars should not be set initially.");
    }

    /**
     * Tests setting the game as ended and verifies that the status updates correctly.
     */
    @Test
    public void testSetGameEnded() {
        statusModel.setGameEnded();
        assertTrue(statusModel.isGameEnded(), "Game should be marked as ended.");
    }

    /**
     * Tests setting the number of stars earned and verifies correct retrieval.
     */
    @Test
    public void testSetStars() {
        statusModel.setStars(3);
        Optional<Integer> stars = statusModel.getEndStars();
        assertTrue(stars.isPresent(), "Stars should be set.");
        assertEquals(3, stars.get(), "Stars value should match the assigned value.");
    }
}

