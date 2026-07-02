package talisman.util;

import java.util.Random;

/**
 * Utility used to roll dices.
 * 
 * @author Alberto Arduini
 *
 */
public final class DiceUtils {

    private static final Random RAND = new Random();

    private DiceUtils() {
    }

    public static int rollDice(final DiceType diceType) {
        return RAND.nextInt(diceType.getFaces() - diceType.getMinValue() + 1) + diceType.getMinValue();
    }
}
