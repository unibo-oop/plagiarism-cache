package gamegraphics;

import java.awt.Color;
import java.util.Map;
import java.util.Set;
import pair.Pair;
import view.View;

/**
 * Interface for the ViewGame.
 *
 */
public interface ViewGame extends View {
    /**
     * Opens the Pause Menu and waits until it closes.
     * 
     * @param score : score of the game
     */
    void pauseMenu(int score);

    /**
     * Opens the GameOver Menu and waits until it closes.
     * 
     * @param score : score of the game
     */
    void gameOverMenu(int score);

    /**
     * Sets the Score of the Score Label.
     * 
     * @param score : score of the game
     */
    void setScore(int score);

    /**
     * Draws the Piece on the main grid.
     * 
     * @param piece : set of the current piece
     * @param color : color of the current piece
     */
    void drawPiece(Set<Pair<Integer, Integer>> piece, Color color);

    /**
     * Draws the Projection of the Piece on the main grid.
     * 
     * @param piece : set of the projection
     * @param color : color of the projection
     */
    void drawProjection(Set<Pair<Integer, Integer>> piece, Color color);

    /**
     * Draws the board on the main grid.
     * 
     * @param board : Map of Pair<Integer,Integer> (keys) and Color (values)
     */
    void drawBoard(Map<Pair<Integer, Integer>, Color> board);

    /**
     * Draws the Piece on the Holdbox.
     * 
     * @param piece : set of the piece
     * @param color : color of the piece
     */
    void drawHoldbox(Set<Pair<Integer, Integer>> piece, Color color);

    /**
     * Draws the Piece on the Preview Box.
     * 
     * @param piece : set of the piece
     * @param color : color of the piece
     */
    void drawPreview(Set<Pair<Integer, Integer>> piece, Color color);
}
