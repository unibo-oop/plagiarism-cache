package model.dice;

import java.util.Random;
/**
 *
 *
 */
public class Dice {

    private static final int FACES = 6;

    private int number;
    /**
     * Dice constructor.
     */
    public Dice() {
        this.rollDice();
    }
    /**
     * 
     * @return result of the roll.
     */
    public int getNumber() {
        return number;
    }
    /**
     * Change value of the dice.
     */
    public final void rollDice() {
        this.number = new Random().nextInt(FACES) + 1;
    }
}
