package migglione.view.impl.scenesimpl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

import java.util.Arrays;
import java.util.List;

import javax.swing.JTextArea;

/**
 * BorderedLine class is used to replace text 
 * massage and replace it whit custom written
 * whit colored border.
 */
public class BorderedLine extends JTextArea {

    private static final long serialVersionUID = 976979868L;
    private static final float BORDER_HUE = 0.63f;
    private static final float BORDER_SATURATION = 0.62f;
    private static final float BORDER_BRIGHTNESS = 0.90f;
    private static final float TEXT_HUE = 0.353f;
    private static final float TEXT_SATURATION = 0.56f;
    private static final float TEXT_BRIGHTNESS = 0.86f;

    private static final float STROKE = 5f;
    private final Color borderedColor = Color.getHSBColor(
        BORDER_HUE,
        BORDER_SATURATION,
        BORDER_BRIGHTNESS
    );
    private final Color textColor = Color.getHSBColor(
        TEXT_HUE,
        TEXT_SATURATION,
        TEXT_BRIGHTNESS
    );

    /**
     * Constructor for create custom colored border.
     */
    public BorderedLine() {
        super();
    }

    @Override
    protected final void paintComponent(final Graphics graphics) {
 
        final Graphics2D graphic = (Graphics2D) graphics;

        graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphic.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        final String text = getText();
        if (text == null || text.isEmpty()) {
            return;
        }

        final FontMetrics metrics = graphic.getFontMetrics(getFont());

        final int tx = getInsets().left;
        final int ty = metrics.getAscent() + getInsets().top;
        final int height = metrics.getHeight();

        final List<String> lines = Arrays.asList(text.split("\n"));

        for (int i = 0; i < lines.size(); i++) {

            final TextLayout layout = new TextLayout(lines.get(i), getFont(), graphic.getFontRenderContext());
            final AffineTransform transform = AffineTransform.getTranslateInstance(tx, ty + (i * height));
            final Shape outline = layout.getOutline(transform);

            graphic.setColor(borderedColor);
            graphic.setStroke(new BasicStroke(STROKE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphic.draw(outline);

            graphic.setColor(textColor);
            graphic.fill(outline);
        }
    }
}
