package it.unibo.modularcheckers;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;

import com.google.common.base.Optional;
import org.junit.Test;

import it.unibo.modularcheckers.checkers.model.piece.Man;
import it.unibo.modularcheckers.model.Color;
import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.Pair;
import it.unibo.modularcheckers.model.move.Step;
import it.unibo.modularcheckers.model.move.StepImpl;
import it.unibo.modularcheckers.util.CoordinateFactory;
import it.unibo.modularcheckers.util.InfiniteXCoordinateFactory;

/**
 * Basic Step Test.
 */
public class StepTest {

    private final CoordinateFactory cFactory = new InfiniteXCoordinateFactory();
    private final Step s0 = new StepImpl(cFactory.next(), new Pair<>(cFactory.next(), new Man(Color.WHITE)));
    private final Step s1 = new StepImpl(cFactory.next(), new Pair<>(cFactory.next(), new Man(Color.BLACK)));
    private final Step s2 = new StepImpl(cFactory.next(), new Pair<>(cFactory.next(), new Man(Color.WHITE)));
    private final Step sDead = new StepImpl(cFactory.next());

    /**
     * Testing Equals method.
     */
    @Test
    public void testEqualsStep() {
        final Step effectiveS0 = new StepImpl(new Coordinate(0, 0),
                new Pair<>(new Coordinate(1, 0), new Man(Color.WHITE)));
        final Step effectiveS1 = new StepImpl(new Coordinate(1, 1),
                new Pair<>(new Coordinate(2, 1), new Man(Color.BLACK)));
        final Step effectiveS2 = new StepImpl(new Coordinate(2, 2),
                new Pair<>(new Coordinate(3, 2), new Man(Color.WHITE)));
        final Step effectiveSDead = new StepImpl(new Coordinate(3, 3));
        System.out.println("Now testing equals method. False Cases.");
        assertFalse("s0 is not equal to s1", s0.equals(s1));
        assertFalse("s0 is not equal to s2", s0.equals(s2));
        assertFalse("s0 is not equal to sDead", s0.equals(sDead));
        assertFalse("s1 is not equal to s2", s1.equals(s2));
        assertFalse("s1 is not equal to sDead", s1.equals(sDead));
        assertFalse("s2 is not equal to sDead", s2.equals(sDead));
        System.out.println("Now testing equals method. True Cases.");
        assertEquals("Testing equals metod on s0", s0, effectiveS0);
        assertEquals("Testing equals metod on s1", s1, effectiveS1);
        assertEquals("Testing equals metod on s2", s2, effectiveS2);
        assertEquals("Testing equals metod on sDead", sDead, effectiveSDead);
    }

    /**
     * Testing getters method.
     */
    @Test
    public void testGettersMethod() {
        assertEquals("Testing getter method for coordinate of s0.", new Coordinate(0, 0), s0.getCoordinate());
        assertEquals("Testing getter method for coordinate of s1.", new Coordinate(1, 1), s1.getCoordinate());
        assertEquals("Testing getter method for coordinate of s2.", new Coordinate(2, 2), s2.getCoordinate());
        assertEquals("Testing getter method for coordinate of sDead.", new Coordinate(3, 3), sDead.getCoordinate());

        assertEquals("Testing getter method for Pair of s0.",
                Optional.of(new Pair<>(new Coordinate(1, 0), new Man(Color.WHITE))), s0.getDeadPiece());
        assertEquals("Testing getter method for Pair of s1.",
                Optional.of(new Pair<>(new Coordinate(2, 1), new Man(Color.BLACK))), s1.getDeadPiece());
        assertEquals("Testing getter method for Pair of s2.",
                Optional.of(new Pair<>(new Coordinate(3, 2), new Man(Color.WHITE))), s2.getDeadPiece());
        assertEquals("Testing getter method for Pair of sDead.", Optional.absent(), sDead.getDeadPiece());
    }
}
