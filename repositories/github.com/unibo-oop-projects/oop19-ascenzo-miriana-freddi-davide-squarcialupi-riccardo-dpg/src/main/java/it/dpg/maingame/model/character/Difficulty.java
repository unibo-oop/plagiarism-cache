package it.dpg.maingame.model.character;

/**
 * Enum containing various cpu difficulty
 * @author Davide Picchiotti
 * */

public enum Difficulty {
    EASY(0.2f), NORMAL(0.4f), HARD(0.7f);

    private final float multiplier;

    Difficulty(final float multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * @return the multiplier that models the cpu score in minigames
     * */
    public float getMultiplier() {
        return this.multiplier;
    }
}
