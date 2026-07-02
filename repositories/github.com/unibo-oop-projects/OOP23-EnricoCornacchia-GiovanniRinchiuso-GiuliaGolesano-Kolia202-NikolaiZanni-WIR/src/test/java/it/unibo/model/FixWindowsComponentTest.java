package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.GamePerformance;
import it.unibo.common.Pair;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.FixWindowsComponent;
import it.unibo.model.impl.FixedWindowsComponent;
import it.unibo.model.impl.GamePerformanceImpl;

/**
 * Class to test the fix windows component.
 */
class FixWindowsComponentTest {

    private FixWindowsComponent fixWindowsComponent;

    /**
     * Set the variables.
     */
    @BeforeEach
    void setUp() {
        initializeFixWindowsComponent();
    }

    /**
     * Initializes the FixWindowsComponent instance.
     */
    private void initializeFixWindowsComponent() {
        fixWindowsComponent = new FixWindowsComponent();
    }

    /**
     * Test for the method that fixes a window.
     */
    @Test
    void testFixing() {
        final Pair<Double, Double> windowPosition = new Pair<>(0.0, 0.0);
        final EntityFactoryImpl entityFactory = new EntityFactoryImpl(new GamePerformanceImpl(new GameController()));
        final Entity window = entityFactory.createWindows(windowPosition, false);

        final GameController gameController = new GameController();
        final GamePerformance gamePerformance = new GamePerformanceImpl(gameController);
        gamePerformance.addEntity(window);

        final FixWindowsComponent fixWindowsComponent = new FixWindowsComponent();
        fixWindowsComponent.fixing(windowPosition, gamePerformance);

        final FixedWindowsComponent fixedWindowsComponent = (FixedWindowsComponent) window
                .getTheComponent(ComponentType.FIXEDWINDOWS).get();
        assertTrue(fixedWindowsComponent.isFixed(), "Window should be fixed");
    }

    /**
     * Test for the method that gets the right component.
     */
    @Test
    void testGetComponent() {
        assertEquals(ComponentType.FIXWINDOWS, fixWindowsComponent.getComponent());
    }
}
