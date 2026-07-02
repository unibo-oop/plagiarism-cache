package model.dice;

import java.util.Random;

/**
 *
 * Chiara Tarantino.
 * A class that represents a generic dice. 
 *
 */
public abstract class AbstractDice implements Dice {
    private static final int DICEFACES = 6;
    private static long seed;
    private int result;

    /**
     *
     * @return an integer that represents the result of the roll.
     */
    protected int getResult() {
        return this.result;
    }

    /**
     *
     * @return a string that represents the result face of the roll
     */
    protected abstract String getResultFace();

    // template method
    @Override
    public final String getResultFacePath() {
        return (("/dice/" + this.getResultFace() + ".jpg"));
    }

    @Override
    public final int rollAndGetResult() {
        seed = System.currentTimeMillis();
        this.result = new Random(seed).nextInt(DICEFACES) + 1;
        return this.result;
    }
}
