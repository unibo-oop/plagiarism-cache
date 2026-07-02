package controller.gameoptions;

import java.util.Locale;

/** 
 * Enumeration representing possible game's modalities.
 */
public enum Modality {
    /**
     * Player versus computer mode.
     */
    SINGLE_PLAYER(1),
    /**
     * Player versus player mode.
     */
    MULTIPLAYER(2),
    /**
     * Computer versus computer mode.
     */
    SIMULATION(0);

    private final int numberOfHumans;

    Modality(final int numberOfHumans) { 
       this.numberOfHumans = numberOfHumans;
    }

    /**
     * @return .
     */
    public int getNumberOfHumans() {
        return this.numberOfHumans;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ITALIAN).replace("_", " ");
    }
}
