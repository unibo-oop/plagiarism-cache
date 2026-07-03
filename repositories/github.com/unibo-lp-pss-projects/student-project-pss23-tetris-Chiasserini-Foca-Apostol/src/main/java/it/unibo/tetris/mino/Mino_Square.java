package it.unibo.tetris.mino;

import java.awt.Color;

import it.unibo.tetris.mino.api.Block;
import it.unibo.tetris.mino.api.Mino;


/**
 * Class for a "Square" {@link Mino}
 */
public class Mino_Square extends Mino_Impl {

    /**
     * Constructor for {@link Mino_Square} class 
     * which creates a new Mino_Square object with
     * a yellow {@link Color}.
     */
    public Mino_Square() {
        create(Color.yellow);
    }

    /**
     * Sets the x and y coordinates of the {@link Mino_Square}.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public void setXY(int x, int y) {
        //
        // ° °
        // ° °
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x;
        b[1].y = b[0].y + Block.SIZE;
        b[2].x = b[0].x + Block.SIZE;
        b[2].y = b[0].y;
        b[3].x = b[0].x + Block.SIZE;
        b[3].y = b[0].y + Block.SIZE;

    }
}
