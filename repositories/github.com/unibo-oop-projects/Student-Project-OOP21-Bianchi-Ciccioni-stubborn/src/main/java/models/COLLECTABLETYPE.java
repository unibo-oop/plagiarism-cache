package models;

import java.util.Random;

/**
 * This enum contains the possible values that an entity of Collectable type can have
 * inside the game map. It now contains five constants each with a different value (can be expandend
 * into even more constants)
 */
public enum COLLECTABLETYPE {

    DIAMOND(100),
    COIN(50),
    CHALICE(150),
    CROWN(200),
    BAGOFCOINS(200);


    private final int points;

    /**
     * Get the value of a Collectable.
     * 
     * @return The number of points given by the Collectable when picked up
     */
    public int getPoints()  {
        return this.points;
    }

    /**
     * Assign a random value type to a collectable from constants.
     * 
     * @return Random value from constants containted in enum for the collectable
     */
    public static COLLECTABLETYPE getRandomType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    /**
     * This is the constructor of CollectableType and its constants.
     * 
     * @param points The number of points of Collectable, its "value"
     */
    COLLECTABLETYPE(final int points){
        this.points = points;
    }
}
