package gamegraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;
import pair.Pair;

/**
 * Interface for the Logics for the GridPanel.
 *
 */
public interface GridPanelLogics {
    /**
     * Creates all the lines.
     * 
     * @param g : Graphics
     */
    void paintLines(Graphics g);

    /**
     * Slightly changes the width or the height in order to make them divisible by
     * columns and rows respectively.
     * 
     */
    void cancelProtrusion();

    /**
     * Fills the block (x,y) with the color (color).
     * 
     * @param x     : x of the block
     * @param y     : y of the block
     * @param color : color of the block
     * @param g     : Graphics
     */
    void fillBlock(int x, int y, Color color, Graphics g);

    /**
     * Draws the saved Piece.
     * 
     * @param g : Graphics
     */
    void drawPiece(Graphics g);

    /**
     * Gets the Color of the current piece (null if no piece).
     * 
     * @return Color : Color of the current piece
     */
    Color getColor();

    /**
     * Sets the Color of the current piece (null if no piece).
     * 
     * @param color : Color of the current piece
     */
    void setColor(Color color);

    /**
     * Gets the Set of the current piece (empty if no piece).
     * 
     * @return Set<Pair<Integer,Integer>> : Current Piece
     */
    Set<Pair<Integer, Integer>> getPiece();

    /**
     * Sets the Set of the current piece (empty if no piece).
     * 
     * @param piece : Current Piece
     */
    void setPiece(Set<Pair<Integer, Integer>> piece);

    /**
     * Gets the sizes of the Panel. X is width, Y is height
     * 
     * @return Pair<Integer,Integer>> : Size of the Panel
     */
    Pair<Integer, Integer> getSize();

    /**
     * Sets the sizes of the Panel. X is width, Y is height.
     * 
     * @param size : Size of the Panel
     */
    void setSize(Pair<Integer, Integer> size);

    /**
     * Gets the number of lines of the Panel. X is rows, Y is cols.
     * 
     * @return Pair<Integer,Integer>> : number of rows and cols
     */
    Pair<Integer, Integer> getnLines();

    /**
     * Sets the sizes of a box in the Panel. X is boxWidth, Y is boxHeight.
     * 
     * @param boxSize : width and height of a box
     */
    void setBoxSize(Pair<Integer, Integer> boxSize);
}
