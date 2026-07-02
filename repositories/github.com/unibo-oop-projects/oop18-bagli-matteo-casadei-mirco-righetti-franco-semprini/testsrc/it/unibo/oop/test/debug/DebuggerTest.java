package it.unibo.oop.test.debug;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import it.unibo.oop.crossline.debug.Debugger;

/**
 * Test class for Debugger.
 */
public class DebuggerTest {

    /**
     * Test method for {@link it.unibo.oop.test.debug#getInstance()}.
     */
    @Test
    public final void testGetInstance() {
        assertNotNull("Degguer istance is not null", Debugger.getInstance());
    }

}
