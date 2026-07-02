package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.FixedWindowsComponent;

/**
 * Class to test the fixed window component.
 */
class FixedWindowsComponentTest {

    /**
     * Test for the method that sets the isFixed variable.
     */
    @Test
    void testSetFixed() {
        final FixedWindowsComponent fixedWindowsComponent = new FixedWindowsComponent(false);
        fixedWindowsComponent.setFixed();
        assertTrue(fixedWindowsComponent.isFixed(), "Window should be fixed");
    }

    /**
     * Test for the method that gets the isFixed variable.
     */
    @Test
    void testGetFixed() {
        final FixedWindowsComponent fixedWindowsComponent = new FixedWindowsComponent(true);
        assertTrue(fixedWindowsComponent.isFixed(), "Window should be fixed");
    }

    /**
     * Test for the method that gets the right component.
     */
    @Test
    void testGetComponent() {
        final FixedWindowsComponent fixedWindowsComponent = new FixedWindowsComponent(true);
        assertEquals(ComponentType.FIXEDWINDOWS, fixedWindowsComponent.getComponent());
    }
}
