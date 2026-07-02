package unibo.exiled.view.items;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import unibo.exiled.utilities.FontManager;

import java.awt.Color;
import java.util.List;

/**
 * A new styled label for the view.
 */
public class GameLabel extends JLabel {
    private static final long serialVersionUID = 7L;

    private final List<Integer> foregroundColors = List.of(50, 100, 255);
    private final Color foregroundColor = new Color(
            foregroundColors.get(0),
            foregroundColors.get(1),
            foregroundColors.get(2));

    /**
     * The constructor for the new label.
     *
     * @param text The text to insert in the label.
     */
    public GameLabel(final String text) {
        this(text, SwingConstants.LEFT);
    }

    /**
     * The overload of the constructor of the new label.
     *
     * @param text      The text to insert in the label.
     * @param alignment The alignment of the internal text.
     */
    public GameLabel(final String text, final int alignment) {
        super(text, alignment);
        setLabelStyle();
    }

    private void setLabelStyle() {
        setForeground(foregroundColor);
        setFont(FontManager.getCustomFont());
    }

}
