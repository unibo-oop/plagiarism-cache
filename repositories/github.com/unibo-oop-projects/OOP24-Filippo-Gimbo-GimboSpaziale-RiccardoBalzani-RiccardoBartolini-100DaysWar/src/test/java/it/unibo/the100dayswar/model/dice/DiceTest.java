package it.unibo.the100dayswar.model.dice;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.model.dice.api.Dice;
import it.unibo.the100dayswar.model.dice.impl.DiceImpl;

/**
 * Test class for DiceImpl.
 */
class DiceTest {
    private static final int MAX = 6;
    private static final int MIN = 1;
    private static final int REPETITIONS = 100;

    /**
     * Test that the dice roll returns values between 1 and 6.
     */
    @Test
    void testDiceRollRange() {
        final Dice dice = new DiceImpl();

        for (int i = 0; i < REPETITIONS; i++) {
            final int result = dice.roll();
            assertTrue(result >= MIN && result <= MAX, "The result should be between 1 and 6");
        }
    }

    /**
     * Test that the dice produces all possible values over multiple rolls.
     */
    @Test
    void testDiceProducesAllValues() {
        final Dice dice = new DiceImpl();
        final boolean[] results = new boolean[MAX];

        for (int i = 0; i < REPETITIONS; i++) {
            final int roll = dice.roll();
            results[roll - 1] = true;
        }

        for (int i = 0; i < results.length; i++) {
            if (!results[i]) {
                fail("The dice didn't produce this value: " + (i + 1));
            }
        } 
    }
}
