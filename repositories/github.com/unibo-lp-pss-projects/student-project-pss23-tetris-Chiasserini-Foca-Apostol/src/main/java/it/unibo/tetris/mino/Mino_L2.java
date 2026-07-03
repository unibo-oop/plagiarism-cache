package it.unibo.tetris.mino;

import java.awt.Color;

import it.unibo.tetris.mino.api.Mino;
import it.unibo.tetris.mino.api.Block;

/**
 * Class for a 2° version of "L" {@link Mino}
 */
public class Mino_L2 extends Mino_Impl{

    /**
     * Constructor for {@link Mino_L2} class which creates a new Mino_L2 object with
     * a blue {@link Color}.
     */
    public Mino_L2() {
        create(Color.blue);
    }

    /**
     * Sets the x and y coordinates of the {@link Mino_L2}.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public void setXY(int x, int y) {
        //   °
        //   °
        // ° °
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x;
        b[1].y = b[0].y - Block.SIZE;
        b[2].x = b[0].x;
        b[2].y = b[0].y + Block.SIZE;
        b[3].x = b[0].x - Block.SIZE;
        b[3].y = b[0].y + Block.SIZE;
    }

    /**
     * Determines the next direction for the {@link Mino_L2} object to move based on
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
                getDirection3();
                break;
            case 3:
                getDirection4();
                break;
            case 4:
                getDirection1();
                break;
        }
    }

    /**
     * Utility function that moves the blocks of the {@link Mino_L2} object 
     * in the first direction
     */
    private void getDirection1() {
        //   °
        //   °
        // ° °
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y - Block.SIZE;
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y + Block.SIZE;
        tempB[3].x = b[0].x - Block.SIZE;
        tempB[3].y = b[0].y + Block.SIZE;

        updateXY(1);
    }

    /**
     * Utility function that moves the blocks of the {@link Mino_L2} object 
     * in the second direction
     */
    private void getDirection2() {
        //
        // °
        // ° ° °
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x + Block.SIZE;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x - Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x - Block.SIZE;
        tempB[3].y = b[0].y - Block.SIZE;

        updateXY(2);
    }

    /**
     * Utility function that moves the blocks of the {@link Mino_L2} object 
     * in the third direction
     */
    private void getDirection3() {
        // ° °
        // °
        // °
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y + Block.SIZE;
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y - Block.SIZE;
        tempB[3].x = b[0].x + Block.SIZE;
        tempB[3].y = b[0].y - Block.SIZE;

        updateXY(3);
    }

    /**
     * Utility function that moves the blocks of the {@link Mino_L2} object 
     * in the fourth direction
     */
    private void getDirection4() {
        //
        // ° ° °
        //     °
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x - Block.SIZE;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x + Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x + Block.SIZE;
        tempB[3].y = b[0].y + Block.SIZE;

        updateXY(4);
    }
}
