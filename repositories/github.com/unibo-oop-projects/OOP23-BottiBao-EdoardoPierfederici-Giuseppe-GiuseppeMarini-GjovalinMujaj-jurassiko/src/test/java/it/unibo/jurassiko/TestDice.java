package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.dice.api.Dice;
import it.unibo.jurassiko.model.dice.impl.DiceImpl;

/**
 * Test for the Dice Class.
 */
class TestDice {

    private Dice dice;
    private Set<Integer> possibleValues;

    /**
     * Before each test.
     */
    @BeforeEach
    void init() {
        this.dice = new DiceImpl();
        possibleValues = new HashSet<>();
        // CHECKSTYLE: MagicNumber OFF
        // disable for test purpose
        for (int i = 1; i <= 6; i++) {
            possibleValues.add(i);
        }
    }

    /**
     * Test if the init method is successful.
     */
    @Test
    void testInit() {
        assertNotEquals(null, dice);
        for (int i = 1; i <= 6; i++) {
            assertTrue(possibleValues.contains(i));
        }
    }

    /**
     * Test roll method.
     */
    @Test
    void testRoll() {
        for (int i = 0; i < 10; i++) {
            assertTrue(possibleValues.contains(dice.roll()));
        }
    }

    /**
     * Test multiple roll method.
     */
    @Test
    void testMultiple() {
        List<Integer> tempList;

        tempList = dice.rollMultiple(10);

        assertEquals(10, tempList.size());

        // CHECKSTYLE: MagicNumber ON
        for (final int i : tempList) {
            assertTrue(possibleValues.contains(i));
        }
    }

}
