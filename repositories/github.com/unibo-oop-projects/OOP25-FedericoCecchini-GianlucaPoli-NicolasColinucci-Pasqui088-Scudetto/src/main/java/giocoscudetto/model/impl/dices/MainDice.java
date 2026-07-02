package giocoscudetto.model.impl.dices;

import java.util.Random;

import giocoscudetto.model.api.dices.Dice;

/**
 * This class represents the main dice of the game, 
 * it has a method to throw the dice and get the value of the throw.
 */
public class MainDice implements Dice {

    private static final int[] PROBABILITIES = {
        0, 0,
        1, 1, 1, 1, 1,
        2, 2, 2, 2, 2,
        3, 3, 3, 3, 3,
        4, 4, 4, 4, 4,
        5, 5, 5, 5, 5,
        6, 6, 6, 6, 6,
    };
    private final Random random = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public int rollDice() {
        return PROBABILITIES[random.nextInt(PROBABILITIES.length)];
    }

}
