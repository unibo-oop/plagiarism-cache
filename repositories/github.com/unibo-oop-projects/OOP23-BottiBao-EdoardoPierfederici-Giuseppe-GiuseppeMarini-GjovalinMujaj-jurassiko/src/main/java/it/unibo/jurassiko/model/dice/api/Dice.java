package it.unibo.jurassiko.model.dice.api;

import java.util.List;

/**
 * Interface for the Dice.
 */
public interface Dice {

    /**
     * Roll the dice.
     * 
     * @return a random number between 1 to 6
     */
    int roll();

    /**
     * Rolls the dice for 'amount' of times.
     * 
     * @param amount is the amount of rolls
     * @return a list of integers
     */
    List<Integer> rollMultiple(int amount);
}
