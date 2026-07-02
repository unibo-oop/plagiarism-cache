package it.unibo.jtrs.model.api;

/**
 * An interface that models a Tetromino factory. This factory must be able to
 * supply a random Tetromino each time until it has supplied all the existing
 * ones. When this happens, the factory starts over by providing a new random
 * Tetromino.
 *
 * Considering 3 Tetrominos possible (T1, T2, T3), "T1, T3, T2, T3, T1, T2,
 * T2, etc." is a valid sequence and "T1, T1, T3, T2, etc." is an invalid one.
 */
public interface TetrominoFactory {

    /**
     * Provides a random Tetromino.
     *
     * @return the random Tetromino
     */
    Tetromino getRandomTetromino();

}
