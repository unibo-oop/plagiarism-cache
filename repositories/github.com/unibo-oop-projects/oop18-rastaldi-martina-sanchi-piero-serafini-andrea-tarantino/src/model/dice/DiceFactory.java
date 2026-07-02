package model.dice;

/**
 *
 * Chiara Tarantino. 
 * A factory to create different types of dices.
 *
 */
public class DiceFactory {
    /**
     *
     * @param modality
     *            the game modality that determines the dice type.
     * @return the game dice.
     */
    public Dice getDice(final boolean modality) {
        return modality ? new HedgeJumpingDice() : new ClassicDice();
    }
}
