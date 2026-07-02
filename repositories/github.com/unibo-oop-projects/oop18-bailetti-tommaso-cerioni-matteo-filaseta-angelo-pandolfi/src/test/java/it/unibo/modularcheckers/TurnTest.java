package it.unibo.modularcheckers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import it.unibo.modularcheckers.model.Color;
import it.unibo.modularcheckers.model.TurnIterator;
import it.unibo.modularcheckers.model.TurnIteratorSequential;

/**
 * Test for TurnIterator.
 */
public class TurnTest {

    private final List<Color> colorList = Arrays.asList(Color.WHITE, Color.BLACK);
    private final TurnIterator turns = new TurnIteratorSequential(colorList);
    private static final int ATTEMPTS = 20;

    /**
     * Test if the turn order is respected.
     */
    @Test
    public final void testTurnOrder() {
        for (int i = 0; i < ATTEMPTS; i++) {
            assertTrue("No colors are in the skipped list yet.", turns.hasNext());
            if (i % 2 == 0) {
                assertEquals(i + "° turn : WHITE ", Color.WHITE, turns.next());
            } else {
                assertEquals(i + "° turn : BLACK ", Color.BLACK, turns.next());
            }
        }
    }

    /**
     * Test skipColor and reviveColor method.
     */
    @Test
    public final void testSkipAndRevive() {
        this.turns.skipColor(Color.BLACK);
        for (int i = 0; i < ATTEMPTS; i++) {
            assertTrue("Still one color is present.", turns.hasNext());
            assertEquals(i + "° turn : WHITE ", Color.WHITE, turns.next());
        }
        this.turns.skipColor(Color.WHITE);
        assertFalse("No colors left.", turns.hasNext());
        this.turns.reviveColor(Color.BLACK);
        for (int i = 0; i < ATTEMPTS; i++) {
            assertTrue("Still one color is present.", turns.hasNext());
            assertEquals(i + "° turn : BLACK ", Color.BLACK, turns.next());
        }
        this.turns.reviveColor(Color.WHITE);
    }

    /**
     * Test if all exception are thrown correctly.
     */
    @Test
    public final void testException() {
        try {
            this.turns.reviveColor(Color.WHITE);
            fail("Exception not thrown.");
        } catch (IllegalArgumentException e) {
            System.out.println("Exception thrown correctly: " + e.toString());
        }

        this.turns.skipColor(Color.WHITE);
        try {
            this.turns.skipColor(Color.WHITE);
            fail("Exception not thrown.");
        } catch (IllegalArgumentException e) {
            System.out.println("Exception thrown correctly: " + e.toString());
        }

    }

}
