package it.unibo.monoopoly.model.gameboard.impl;

import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.monoopoly.model.gameboard.api.Dices;

/**
 * This class implement the {@link Dices} interface to simulate dice rolling in
 * the game.
 */
public class DicesImpl implements Dices {

    static final int DICE_FACES = 6;

    private Pair<Integer, Integer> currentRoll;
    private int totalResult;
    private final Random random = new Random();

    /**
     * Initialize the two fields.
     */
    public DicesImpl() {
        this.currentRoll = null;
        this.totalResult = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollDices() {
        final int firstRoll = this.random.nextInt(DICE_FACES) + 1;
        final int secondRoll = this.random.nextInt(DICE_FACES) + 1;
        this.totalResult = firstRoll + secondRoll;
        this.currentRoll = Pair.of(firstRoll, secondRoll);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getDices() {
        return this.currentRoll;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getResult() {
        return this.totalResult;
    }
}
