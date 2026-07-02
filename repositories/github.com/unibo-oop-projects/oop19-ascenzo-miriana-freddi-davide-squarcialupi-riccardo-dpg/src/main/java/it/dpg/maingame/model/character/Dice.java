package it.dpg.maingame.model.character;

/**
 * Enum containing the different dices of the game
 * @author Davide Picchiotti
 * */

public enum Dice {
    D4(4), D6(6), D8(8), D10(10);

    private final int faces;

    Dice(int faces) {
        this.faces = faces;
    }

    /**
     * @return the number of faces of the dice
     * */
    public int getFaces() {
        return this.faces;
    }
}
