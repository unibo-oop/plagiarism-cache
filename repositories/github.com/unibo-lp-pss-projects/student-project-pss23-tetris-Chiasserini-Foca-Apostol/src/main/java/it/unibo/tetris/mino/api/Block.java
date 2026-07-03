package it.unibo.tetris.mino.api;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;

import it.unibo.tetris.PlayManager;

/**
 * Class for a {@link Block} that extends {@link Rectangle}
 */
public class Block extends Rectangle {
    
    /**
     * Constant for the margin between the {@link Block}s
     */
    public static final int MARGIN = 2;

    /**
     * Constant for the size of the {@link Block}
     */
    public static final int SIZE = 30;

    /**
     * Coordinates for the {@link Block}
     */
    public int x, y;

    /**
     * {@link Color} for the {@link Block}
     */
    public Color c;

    /**
     * Constructor for {@link Block} class which creates a new Block object with
     * a given {@link Color}.
     * 
     * * @param c the {@link Color} to be used.
     */
    public Block(Color c) { 
        this.c = c;
    }

    /**
      * Draws the {@link Block} on the given {@link Graphics2D} object.
      *
      * @param g2 The {@link Graphics2D} object to draw on.
      */
    public void draw(Graphics2D g2) { 
        g2.setColor(c);
        g2.fillRoundRect(x + MARGIN, 
                         y + PlayManager.TITLE_HEIGHT + MARGIN, 
                         SIZE - (MARGIN * 2), 
                         SIZE - (MARGIN * 2), 
                         8, 
                         8);
    }
}
