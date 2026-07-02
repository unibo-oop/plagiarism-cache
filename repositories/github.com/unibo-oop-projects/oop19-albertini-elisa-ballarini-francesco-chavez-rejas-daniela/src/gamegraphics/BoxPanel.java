package gamegraphics;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Special JPanel used for the grid of the holdbox and preview box.
 */
public class BoxPanel extends JPanel {
    private static final long serialVersionUID = 6793749186342883858L;
    private final BoxPanelLogics logics; // Logics for the BoxPanel

    /**
     * @param width : Width of the Panel
     * @param height : Height of the Panel
     * @param rows : Rows of the Panel
     * @param cols : Columns of the Panel
     */
    public BoxPanel(final int width, final int height, final int rows, final int cols) {
        this.logics = new BoxPanelLogicsImpl(width, height, rows, cols);
    }

    @Override
    public final void paintComponent(final Graphics g) { // Paint Method that gets called with repaint()
        super.paintComponent(g);

        this.logics.paintLines(g);
        this.logics.drawPiece(g);
    }

    /**
     * @return logics : Logics of the BoxPanel
     */
    public final BoxPanelLogics getLogics() {
        return this.logics;
    }
}
