package it.unibo.jtrs.model.impl;

import it.unibo.jtrs.model.api.PreviewModel;
import it.unibo.jtrs.model.api.Tetromino;
import it.unibo.jtrs.model.api.TetrominoFactory;

/**
 * PreviewModel implementation.
 */
public class PreviewModelImpl implements PreviewModel {

    private final TetrominoFactory factory = new TetrominoFactoryImpl();
    private Tetromino current = factory.getRandomTetromino();

    /**
    * {@inheritDoc}
    */
    @Override
    public void next() {
       this.current = this.factory.getRandomTetromino();
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public Tetromino current() {
        return this.current.copy();
    }

}
