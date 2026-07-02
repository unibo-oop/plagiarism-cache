package it.dpg.minigames.punchygame.model;

/**
 * Basic implementation of Score
 * @author Davide Picchiotti
 * @see Score
 * */

public class ScoreImpl implements Score {
    private int score;
    private int multiplier;
    private int combo;

    public ScoreImpl() {
        score = 0;
        multiplier = 1;
        combo = 0;
    }

    @Override
    public void incrementScore() {
        score += multiplier;
        combo++;
        multiplier = (combo + 10) / 10;
    }

    @Override
    public int getPoints() {
        return score;
    }

    @Override
    public int getMultiplier() {
        return multiplier;
    }

    @Override
    public void resetCombo() {
        multiplier = 1;
        combo = 0;
    }
}
