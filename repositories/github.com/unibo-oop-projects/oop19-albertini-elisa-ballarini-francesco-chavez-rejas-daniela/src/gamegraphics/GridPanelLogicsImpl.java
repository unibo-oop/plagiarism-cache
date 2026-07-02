package gamegraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;
import pair.Pair;

/**
 * Logics Class for the GridPanel.
 */
public class GridPanelLogicsImpl implements GridPanelLogics {
    //X is width, Y is height
    private Pair<Integer, Integer> size;
    //X is rows, Y is cols
    private final Pair<Integer, Integer> nLines;
    //X is boxWidth, Y is boxHeight
    private Pair<Integer, Integer> boxSize;
    private Set<Pair<Integer, Integer>> piece;
    private Color color;

    /**
     * @param width : Width of the Panel
     * @param height : Height of the Panel
     * @param rows : Rows of the Panel
     * @param cols : Columns of the Panel
     */
    public GridPanelLogicsImpl(final int width, final int height, final int rows, final int cols) {
        this.size = new Pair<>(width, height);
        this.nLines = new Pair<>(rows, cols);
        this.boxSize = new Pair<>(this.size.getY() / this.nLines.getX(),
                this.size.getX() / this.nLines.getY());
        this.piece = new HashSet<>();
        this.color = null;
        this.cancelProtrusion();
    }

    @Override
    public final void paintLines(final Graphics g) {
        for (int i = 0; i <= this.nLines.getX(); i++) { // Draws the game rows
            g.drawLine(0, i * this.boxSize.getX(), this.size.getX(), i * this.boxSize.getY());
        }

        for (int i = 0; i <= this.nLines.getY(); i++) { // Draws the game columns
            g.drawLine(i * this.boxSize.getY(), 0, i * this.boxSize.getX(), this.size.getY());
        }
    }

    @Override
    public final void cancelProtrusion() {
        while (this.size.getX() % this.nLines.getY() != 0) {
            this.size = new Pair<>(this.size.getX() - 1, this.size.getY());
        }
        while (this.size.getY() % this.nLines.getX() != 0) {
            this.size = new Pair<>(this.size.getX(), this.size.getY() - 1);
        }
    }

    @Override
    public final void fillBlock(final int x, final int y, final Color color, final Graphics g) {
        g.setColor(color);
        g.fillRect((y) * this.boxSize.getX() + 1, // +1 and -1 are used to avoid coloring the lines
                (x) * this.boxSize.getY() + 1, this.boxSize.getX() - 1, this.boxSize.getY() - 1);
    }

    @Override
    public final void drawPiece(final Graphics g) {
        // It draws the piece
        for (final Pair<Integer, Integer> coordinate : this.getPiece()) {
            this.fillBlock(coordinate.getX(), coordinate.getY(), this.getColor(), g);
        }
    }

    @Override
    public final Color getColor() {
        return this.color;
    }

    @Override
    public final void setColor(final Color color) {
        this.color = color;
    }

    @Override
    public final Set<Pair<Integer, Integer>> getPiece() {
        return this.piece;
    }

    @Override
    public final void setPiece(final Set<Pair<Integer, Integer>> piece) {
        this.piece = piece;
    }

    @Override
    public final Pair<Integer, Integer> getSize() {
        return this.size;
    }

    @Override
    public final void setSize(final Pair<Integer, Integer> size) {
        this.size = size;
    }

    @Override
    public final Pair<Integer, Integer> getnLines() {
        return this.nLines;
    }

    @Override
    public final void setBoxSize(final Pair<Integer, Integer> boxSize) {
        this.boxSize = boxSize;
    }
}
