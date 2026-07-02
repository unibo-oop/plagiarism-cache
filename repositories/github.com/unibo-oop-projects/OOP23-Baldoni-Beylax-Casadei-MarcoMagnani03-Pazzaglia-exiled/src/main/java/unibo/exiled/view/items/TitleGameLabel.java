package unibo.exiled.view.items;

import javax.swing.JLabel;

import unibo.exiled.utilities.FontManager;

import java.awt.Color;

/**
 * A label with a title.
 */
public final class TitleGameLabel extends JLabel {
    private static final long serialVersionUID = 9L;
    private static final int RED = 255;
    private static final int GREEN = 100;
    private static final int BLUE = 50;
    private static final int FONT_SIZE = 40;
    private final Color foregroundColor = new Color(RED, GREEN, BLUE);

    /**
     * Constructs the label with a title.
     *
     * @param text The title.
     */
    public TitleGameLabel(final String text) {
        super(text);
        setLabelStyle();
    }

    private void setLabelStyle() {
        setForeground(foregroundColor);

        setFont(FontManager.getCustomFont(FONT_SIZE));
    }
}
