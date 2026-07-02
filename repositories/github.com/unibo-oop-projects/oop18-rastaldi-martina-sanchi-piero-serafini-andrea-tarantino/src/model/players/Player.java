package model.players;

import utilities.Colors;

/**
 * This class can be used to memorize a player and its data.
 * Andrea Serafini.
 *
 */
public final class Player implements User {

    /**
     *
     */
    private static final long serialVersionUID = -7610886613845823536L;
    private String name;
    private Colors color;
    private int turn;

    /**
     * Constructor.
     *
     * @param name
     *            the name of the player
     * @param color
     *            the color of the player
     */
    public Player(final String name, final Colors color) {
        this.name = name;
        this.color = color;
        this.turn = 0;
    }

    /**
     * Constructor for a player with an initial number of turn played.
     * @param name
     *            the name of the player
     * @param color
     *            the color of the player
     * @param turn
     *            the number of turn the player have played
     */
    public Player(final String name, final Colors color, final int turn) {
        this.name = name;
        this.color = color;
        this.turn = turn;
    }


    @Override
    public void changeColor(final Colors color) {
        this.color = color;
    }

    @Override
    public void changeName(final String name) {
        this.name = name;
    }

    @Override
    public Colors getColor() {
        return this.color;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getTurns() {
        return this.turn;
    }

    @Override
    public void incrementTurn() {
        this.turn++;
    }

    @Override
    public void resetTurn() {
        this.turn = 0;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.color;
    }

}
