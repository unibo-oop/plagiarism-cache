package giocoscudetto.model.impl.dices;

import java.util.Random;

import giocoscudetto.model.api.dices.Dice;

/**
 * This class represents the result dice of the game, 
 * it has a method to throw the dice and get the value of the throw.
 */
public class ResultDice implements Dice {

    private static final int[] PROBABILITIES = {
        3, 3, 3, 3, 3,
        2, 2, 2, 2, 2, 2, 2,
        1, 1, 1, 1, 1, 1, 1, 1, 1,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
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
