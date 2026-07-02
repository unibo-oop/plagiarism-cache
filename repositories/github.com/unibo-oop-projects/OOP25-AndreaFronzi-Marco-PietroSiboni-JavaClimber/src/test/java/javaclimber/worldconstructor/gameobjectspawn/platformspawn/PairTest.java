package javaclimber.worldconstructor.gameobjectspawn.platformspawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.Pair;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PairImpl;

/**
 * Test for the {@link Pair}.
 */
class PairTest {

    private static final int X = 10;
    private static final int Y = 20;

    /**
     * The PairImpl instance to test.
     */
    private Pair<Integer, Integer> pairPlatform;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    void setUpPair() {
        this.pairPlatform = new PairImpl<>(X, Y);
    }

    /**
     * Test for getting the first element of the pair.
     */
    @Test
    void getterXTest() {
        assertEquals(X, this.pairPlatform.getX());
    }

    /**
     * Test for getting the second element of the pair.
     */
    @Test
    void getterYTest() {
        assertEquals(Y, this.pairPlatform.getY());
    }

}
