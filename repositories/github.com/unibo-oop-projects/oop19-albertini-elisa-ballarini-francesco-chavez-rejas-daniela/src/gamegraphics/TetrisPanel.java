package gamegraphics;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Special JPanel used for the main game grid.
 */
public class TetrisPanel extends JPanel {
    private static final long serialVersionUID = 8660098482297818678L;
    private final TetrisPanelLogics logics;

    /**
     * @param width : Width of the Panel
     * @param height : Height of the Panel
     * @param rows : Rows of the Panel
     * @param cols : Columns of the Panel
     */
    public TetrisPanel(final int width, final int height, final int rows, final int cols) {
        this.logics = new TetrisPanelLogicsImpl(width, height, rows, cols);
    }

    @Override
    public final void paint(final Graphics g) { // Paint Method that gets called with repaint()
        super.paint(g);

        this.logics.paintLines(g);

        this.logics.drawPiece(g); // Draws the falling piece, then the projection, then the rest of the board
        this.logics.drawProjection(g);
        this.logics.drawBoard(g);
    }

    /**
     * @return logics : Logics of the TetrisPanel
     */
    public final TetrisPanelLogics getLogics() {
        return this.logics;
    }
}
