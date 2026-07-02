package it.unibo.jtrs.model.api;

/**
 * An interface modelling the preview of the next Tetromino.
 */
public interface PreviewModel {

    /**
     * Returns the current Tetromino in the preview (next to show in game).
     *
     * @return the current Tetromino
     */
    Tetromino current();

    /**
     * Updates model with another Tetromino.
     */
    void next();

}
