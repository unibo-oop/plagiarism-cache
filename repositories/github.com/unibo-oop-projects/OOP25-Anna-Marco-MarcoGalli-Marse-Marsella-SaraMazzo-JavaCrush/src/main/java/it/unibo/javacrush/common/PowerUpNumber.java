package it.unibo.javacrush.common;

import it.unibo.javacrush.powerup.impl.AbstractPowerUp;
import it.unibo.javacrush.powerup.impl.RemoveCell;
import it.unibo.javacrush.powerup.impl.RemoveRow;
import it.unibo.javacrush.powerup.impl.RemoveType;

/**
 * Represents all the PowerUps of the game.
 */
public enum PowerUpNumber {

    SINGLECELL,
    ROW,
    TYPE;

    /**
     * Creates and returns an AbstractPowerUp.
     * 
     * @param i the index of the AbstractPowerUp to be created.
     * @return an AbstractPowerUp.
     */
    public static AbstractPowerUp getPowerUp(final int i) {
        return switch (i) {
            case 0 -> new RemoveCell();
            case 1 -> new RemoveRow();
            case 2 -> new RemoveType();
            default -> null;
        };
    }
}
