package model.dice;
/**
 * 
 * Chiara Tarantino.
 * An interface that represent a Dice.
 * 
 */
public interface Dice {
    /**
     * 
     * @return the result of the dice roll
     */
    int rollAndGetResult();
    /**
     * 
     * @return the path to the face image of the dice obtained as result of the roll
     */
    String getResultFacePath();
}
