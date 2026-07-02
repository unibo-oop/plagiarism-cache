package it.unibo.jtrs.model.impl;

import it.unibo.jtrs.model.api.ScoreModel;

/**
 * ScoreModel implementation.
 */
public class ScoreModelImpl implements ScoreModel {

    private static final int LEVEL_FACTOR = 10;
    private static final int L1_POINTS = 40;
    private static final int L2_POINTS = 100;
    private static final int L3_POINTS = 300;
    private static final int L4_POINTS = 1200;

    private int level;
    private int score;
    private int deletedLines;
    private int lastDeleted;

    /**
     * Constructor.
     */
    public ScoreModelImpl() {
        this.level = 0;
        this.score = 0;
        this.deletedLines = 0;
        this.lastDeleted = 0;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public int getLevel() {
        return this.level;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public int getScore() {
        return this.score;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void evaluate(final int lines) {
        this.lastDeleted = lines;
        this.setLevel(lines);
        final int evaluatedScore = switch (lines) {
            case 1 -> L1_POINTS * (this.level + 1);
            case 2 -> L2_POINTS * (this.level + 1);
            case 3 -> L3_POINTS * (this.level + 1);
            case 4 -> L4_POINTS * (this.level + 1);
            default -> 0;
        };
        this.score = this.score + evaluatedScore;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public int getLines() {
        return this.lastDeleted;
    }

    private void setLevel(final int lines) {
        this.deletedLines = this.deletedLines + lines;
        this.level = this.deletedLines / ScoreModelImpl.LEVEL_FACTOR;
    }

}
