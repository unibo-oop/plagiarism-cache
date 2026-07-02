package it.unibo.controller.interfaces;

/**
 * Interface for handling the logic of generating and dropping Puyos.
 * By implementing this interface, a class can control how Puyos are generated,
 * dropped, and initialized within the game.
 */
public interface PuyoDropperInterface {

    /**
     * Method to spawn a new Puyo with a random color and drop it into the grid.
     * The logic ensures that the Puyo is placed in a valid column.
     */
    void spawnAndDropPuyo();

    /**
     * Method that handles the logic of dropping Puyos on the grid.
     * Each Puyo that has an empty space beneath it will move down.
     */
    void dropPuyo();

    /**
     * Initializes the grid by placing random Puyos in the last two rows of the grid.
     */
    void initialize();

    /**
     * Fills the grid with a specified number of randomly generated Puyos.
     *
     * @param puyoCount The number of Puyos to generate and drop into the grid.
     */
    void fillGridRandomly(int puyoCount);
}
