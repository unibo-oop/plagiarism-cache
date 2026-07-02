package it.unibo.the100dayswar.model.dice.api;

/**
 * Represents a die that can be rolled to generate a random integer result.
 */
public interface Dice {

    /**
     * Rolls the die and returns a random integer value.
     *
     * @return the result of the die roll as an integer
     */
    int roll();
}
