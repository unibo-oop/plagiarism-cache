package view;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * This class menage the drawing of text.
 */
public class TextDrawer extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Integer XLOCATION = 40;
    private static final Integer YLOCATION = 15;
    private final String text;

    /**
     * Constructor of the class.
     * 
     * @param text
     *                 The string text to trasform in an image.
     */
    public TextDrawer(final String text) {
        super();
        this.text = text;
    }

    /**
     * @param g
     * @param text
     *                 Text to draw.
     * @param x
     *                 X coordinate
     * @param y
     *                 Y coordinate
     */
    private void drawString(final Graphics g, final String text, final int x, final int y) {
        int tmpY = y;
        for (final String line : text.split("\n")) {
            tmpY += g.getFontMetrics().getHeight();
            g.drawString(line, DimensionUtils.xForScreenDimension(x), DimensionUtils.yForScreenDimension(tmpY));
        }
    }

    @Override
    public final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        drawString(g, this.text, XLOCATION, YLOCATION);
    }

}
