package gamegraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import pair.Pair;

/**
 * Logics for the TetrisPanel.
 */
public class TetrisPanelLogicsImpl extends BoxPanelLogicsImpl implements TetrisPanelLogics {
    private Map<Pair<Integer, Integer>, Color> board;
    private Set<Pair<Integer, Integer>> projectionPiece;
    private Color projectionColor;

    /**
     * @param width : Width of the Panel
     * @param height : Height of the Panel
     * @param rows : Rows of the Panel
     * @param cols : Columns of the Panel
     */
    public TetrisPanelLogicsImpl(final int width, final int height, final int rows, final int cols) {
        super(width, height, rows, cols);

        this.board = new HashMap<>();
        this.projectionPiece = new HashSet<>();
        this.projectionColor = null;
    }

    @Override
    public final void drawBoard(final Graphics g) {
        for (final Pair<Integer, Integer> coordinate : this.board.keySet()) {
            this.fillBlock(coordinate.getX(), coordinate.getY(), this.board.get(coordinate), g);
        }
    }

    @Override
    public final void drawProjection(final Graphics g) {
        for (final Pair<Integer, Integer> coordinate : this.projectionPiece) {
            this.fillBlock(coordinate.getX(), coordinate.getY(), this.projectionColor, g);
        }
    }

    @Override
    public final void setBoard(final Map<Pair<Integer, Integer>, Color> board) {
        this.board = board;
    }

    @Override
    public final void setProjection(final Set<Pair<Integer, Integer>> projectionPiece) {
        this.projectionPiece = projectionPiece;
    }

    @Override
    public final void setProjectionColor(final Color projectionColor) {
        this.projectionColor = projectionColor;
    }
}
