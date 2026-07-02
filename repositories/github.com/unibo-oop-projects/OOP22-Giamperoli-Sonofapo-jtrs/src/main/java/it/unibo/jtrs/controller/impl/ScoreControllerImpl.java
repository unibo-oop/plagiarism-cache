package it.unibo.jtrs.controller.impl;

import it.unibo.jtrs.controller.api.ScoreController;
import it.unibo.jtrs.model.api.ScoreModel;
import it.unibo.jtrs.model.impl.ScoreModelImpl;

/**
 * ScoreController implementation.
 */
public class ScoreControllerImpl implements ScoreController {

    private final ScoreModel model;

    /**
     * Constructor.
     */
    public ScoreControllerImpl() {
        this.model = new ScoreModelImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLevel() {
        return this.model.getLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.model.getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void evaluate(final int lines) {
        this.model.evaluate(lines);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int returnRemoved() {
        return this.model.getLines();
    }
}
