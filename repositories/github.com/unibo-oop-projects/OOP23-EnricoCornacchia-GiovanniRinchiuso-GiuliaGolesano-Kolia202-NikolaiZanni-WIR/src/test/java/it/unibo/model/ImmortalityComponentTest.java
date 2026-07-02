package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.ImmortalityComponent;
import it.unibo.model.impl.LivesComponent;

/**
 * Test class for {@link ImmortalityComponent}.
 */
final class ImmortalityComponentTest {

    /**
     * The ImmortalityComponent instance to be tested.
     */
    private ImmortalityComponent immortalityComponent;

    /**
     * The LivesComponent instance to be tested.
     */
    private LivesComponent livesComponent;

    private static final int IMMORTALITY_DURATION_MS = 10_001;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        initializeComponents();
    }

    /**
     * Initializes the components.
     */
    private void initializeComponents() {
        immortalityComponent = new ImmortalityComponent();
        livesComponent = new LivesComponent();
    }

    /**
     * Tests the setImmortality method.
     */
    @Test
    void testSetImmortality() {
        immortalityComponent.setImmortality(livesComponent);
        assertTrue(livesComponent.isImmortality());
    }

    /**
     * Tests the chekStopImmortality method.
     * 
     * @throws InterruptedException if the sleep is interrupted
     */
    @Test
    void testChekStopImmortality() throws InterruptedException {
        immortalityComponent.setImmortality(livesComponent);
        assertTrue(livesComponent.isImmortality());
        Thread.sleep(IMMORTALITY_DURATION_MS);
        immortalityComponent.chekStopImmortality(livesComponent);
        assertFalse(livesComponent.isImmortality());
    }

    /**
     * Tests the getComponent method.
     */
    @Test
    void testGetComponent() {
        assertEquals(ComponentType.IMMORTALITY, immortalityComponent.getComponent());
    }
}
