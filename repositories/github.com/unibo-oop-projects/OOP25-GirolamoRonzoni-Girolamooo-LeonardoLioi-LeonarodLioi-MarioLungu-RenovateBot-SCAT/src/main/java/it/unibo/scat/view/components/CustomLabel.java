package it.unibo.scat.view.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import it.unibo.scat.view.UIConstants;

/**
 * Custom label class.
 */
public final class CustomLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    private final String baseText;
    private final String hoverText;

    /**
     * Custom label constructor.
     * 
     * @param text the text that the label contains.
     */
    public CustomLabel(final String text) {
        super(text);

        baseText = text;
        hoverText = "<" + baseText + ">";

        final FontMetrics fm = getFontMetrics(UIConstants.FONT_XXL_HOVER);
        final int w = fm.stringWidth(hoverText);
        final int h = fm.getHeight();

        final Dimension fixed = new Dimension(w, h);
        setPreferredSize(fixed);
        setMaximumSize(fixed);
        setMinimumSize(fixed);

        setFocusable(false);
        setFont(UIConstants.FONT_XXL);
        setForeground(Color.WHITE);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                setForeground(Color.GREEN);
                setFont(UIConstants.FONT_XXL_HOVER);
                setText(hoverText);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                setForeground(Color.WHITE);
                setFont(UIConstants.FONT_XXL);
                setText(baseText);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }
        });

    }
}
