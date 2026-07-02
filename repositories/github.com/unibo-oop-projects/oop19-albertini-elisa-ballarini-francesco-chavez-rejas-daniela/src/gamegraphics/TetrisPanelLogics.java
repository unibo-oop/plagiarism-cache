package gamegraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.Set;
import pair.Pair;

/**
 * Interface for the Logics for the TetrisPanel.
 *
 */
public interface TetrisPanelLogics extends GridPanelLogics {
    /**
     * Draws the Board (the Map containing all the already fallen pieces).
     * 
     * @param g : Graphics
     */
    void drawBoard(Graphics g);

    /**
     * Draws the Projection of the falling Piece.
     * 
     * @param g : Graphics
     */
    void drawProjection(Graphics g);

    /**
     * Sets the Board (the Map containing all the already fallen pieces).
     * 
     * @param board : Map of Pair<Integer,Integer> as keys and Color as values
     */
    void setBoard(Map<Pair<Integer, Integer>, Color> board);

    /**
     * Sets the Set for the Projection of the falling Piece.
     * 
     * @param projectionPiece : piece of the projection
     */
    void setProjection(Set<Pair<Integer, Integer>> projectionPiece);

    /**
     * Sets the Color for the Projection of the falling Piece.
     * 
     * @param projectionColor : Color of the projection
     */
    void setProjectionColor(Color projectionColor);
}
