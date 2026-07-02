package model.entitiesutil;

import java.util.Random;

/**
 * Every type of {@link PowerUp}.
 */
public enum PowerUpState {

    /**
     * Apple {@link PowerUp}.
     */
    APPLE,

    /**
     * Cherry {@link PowerUp}.
     */
    CHERRY,

    /**
     * Pear {@link PowerUp}.
     */
    PEAR;

    /**
     * Return a random {@link PowerUpState}.
     * 
     * @return the {@link PowerUpState}
     */
    public static PowerUpState getRandom() {
        return values()[new Random().nextInt(values().length)];
    }
}
