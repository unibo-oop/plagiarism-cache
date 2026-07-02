package it.unibo.jtrs.controller.impl;

import it.unibo.jtrs.controller.api.PreviewController;
import it.unibo.jtrs.model.api.PreviewModel;
import it.unibo.jtrs.model.api.Tetromino;
import it.unibo.jtrs.model.impl.PreviewModelImpl;

/**
 * PreviewController implementation.
 */
public class PreviewControllerImpl implements PreviewController {

    private final PreviewModel model;

    /**
     * Constructor.
     */
    public PreviewControllerImpl() {
        this.model = new PreviewModelImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tetromino getCurrentTetromino() {
        return this.model.current();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextTetromino() {
        this.model.next();
    }

}
