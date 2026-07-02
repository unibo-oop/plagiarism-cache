package giocoscudetto.model.api.dices;

/**
 * This interface represents the dice of the game, it has a method to throw the dice and get the value of the throw.
 */
@FunctionalInterface
public interface Dice {

    /**
     * this method throws a dice.
     * 
     * @return the value of the dice throw.
     */
    int rollDice();
}
