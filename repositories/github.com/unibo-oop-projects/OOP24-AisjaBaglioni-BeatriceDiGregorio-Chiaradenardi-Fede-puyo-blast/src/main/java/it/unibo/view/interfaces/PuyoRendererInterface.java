package it.unibo.view.interfaces;

import java.awt.Graphics;

import it.unibo.model.Grid;

/**
 * This interface is used by the class responsible
 * for drawing and rendering the Puyos.
 */
public interface PuyoRendererInterface {
    /**
     * Checks if a Puyo in [row, col] has the same color as the given parameter
     */
    int sameColorNeighbour(Grid grid, int row, int col, String color);

    /**
     * Displays the Puyo at [row, col] inside the {@link Grid}
     */
    void render(Graphics g, Grid grid, int row, int col);
}
