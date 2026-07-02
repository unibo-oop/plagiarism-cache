package it.unibo.jtrs.controller.api;

import it.unibo.jtrs.model.api.Tetromino;

/**
 * An interface that model a preview controller. It acts like a bridge between
 * the preview model and its view.
 */
public interface PreviewController {

    /**
     * Returns the current Tetromino.
     *
     * @return the Tetromino
     */
    Tetromino getCurrentTetromino();

    /**
     * Advances to the next Tetromino.
     */
    void nextTetromino();

}
