package it.unibo.tetris.mino;

import java.awt.Color;

import it.unibo.tetris.mino.api.Block;
import it.unibo.tetris.mino.api.Mino;


/**
 * Class for a "Bar" {@link Mino}
 */
public class Mino_Bar extends Mino_Impl {

    /**
     * Constructor for {@link Mino_Bar} class which creates a new Mino_Z2 object with
     * a cyan {@link Color}.
     */
    public Mino_Bar() {
        create(Color.cyan);
    }
    
    /**
     * Sets the x and y coordinates of the {@link Mino_Bar}.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public void setXY(int x, int y) {
        //
        // ° ° ° °
        //
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x - Block.SIZE;
        b[1].y = b[0].y;
        b[2].x = b[0].x + Block.SIZE;
        b[2].y = b[0].y;
        b[3].x = b[0].x + Block.SIZE * 2;
        b[3].y = b[0].y;

    }

    /**
     * Determines the next direction for the {@link Mino_Bar} object to move based on
     * the given direction
     *
     * @param direction The current direction.
     */
    public void getNextDirection(int direction) {

        switch (direction) {
            case 1:
                getDirection2();
                break;
            case 2:
                getDirection1();
                break;
        }
    }

    /**
     * Utility function that moves the blocks of the {@link Mino_Bar} object 
     * in the first direction
     */
    private void getDirection1() {
        //
        // ° ° ° °
        //
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x - Block.SIZE;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x + Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x + Block.SIZE * 2;
        tempB[3].y = b[0].y;

        updateXY(1);
    }

    /**
     * Utility function that moves the blocks of the {@link Mino_Bar} object 
     * in the second direction
     */
    private void getDirection2() {
        // °
        // °
        // °
        // °
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y - Block.SIZE;
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y + Block.SIZE;
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y + Block.SIZE * 2;

        updateXY(2);
    }
}




