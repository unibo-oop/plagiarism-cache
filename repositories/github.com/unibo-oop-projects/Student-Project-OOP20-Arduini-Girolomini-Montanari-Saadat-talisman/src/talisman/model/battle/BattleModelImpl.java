package talisman.model.battle;

import talisman.util.DiceType;
import talisman.util.DiceUtils;

/**
 * Implementation of the model of the battle.
 * 
 * @author Alice Girolomini
 *
 */

public class BattleModelImpl implements BattleModel {
    private int firstCharScore;
    private int secondCharScore;
    private int firstDice;
    private int secondDice;
    private BattleState currentState;
    private boolean end;

    /**
     * Creates a new battle model.
     * 
     * @param firstCharScore the initial strength or craft of the first character
     * @param secondCharScore the initial strength or craft of the second character
     */
    public BattleModelImpl(final int firstCharScore, final int secondCharScore) {
        this.firstCharScore = firstCharScore;
        this.secondCharScore = secondCharScore;
        this.firstDice = 0;
        this.secondDice = 0;
        this.currentState = BattleState.START;
        this.end = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void diceRoll(final int character) {
        if (character == 1) {
            this.firstDice = DiceUtils.rollDice(DiceType.SIX);
        } else {
            this.secondDice = DiceUtils.rollDice(DiceType.SIX);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void compareScore() {
        if (this.firstCharScore > this.secondCharScore) {
            this.currentState = BattleState.FIRST;
        } else if (this.firstCharScore < this.secondCharScore) {
            this.currentState = BattleState.SECOND;
        } else if (this.firstCharScore == this.secondCharScore) {
            this.currentState = BattleState.STAND_OFF;
        }
        this.end = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnded() {
        return this.end;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BattleState getState() {
        return this.currentState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setScore(final int character, final int value) {
        if (character == 1) {
            this.firstCharScore = value;
        } else {
            this.secondCharScore = value;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDiceRoll(final int character) {
        if (character == 1) {
            return this.firstDice;
        }
        return this.secondDice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore(final int character) {
        if (character == 1) {
            return this.firstCharScore;
        }
        return this.secondCharScore;
    }
}
