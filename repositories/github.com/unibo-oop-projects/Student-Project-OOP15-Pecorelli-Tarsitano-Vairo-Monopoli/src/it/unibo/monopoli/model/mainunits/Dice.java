package it.unibo.monopoli.model.mainunits;

/**
 * This interface represent all the dices used for the game.
 *
 */
public interface Dice {

    /**
     * This method allows to roll the {@link Dice}. It calculates a number from
     * those available of the {@link Dice} and it returns that number.
     * 
     * @return a number from those available of the {@link Dice}
     */
    int roll();

}
