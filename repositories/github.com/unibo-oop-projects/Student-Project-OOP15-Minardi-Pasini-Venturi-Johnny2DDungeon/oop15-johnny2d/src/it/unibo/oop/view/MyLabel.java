package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * A simple class to easily create a {@link javax.swing.JLabel}.
 */
public final class MyLabel {

    private MyLabel() {
    }

    /**
     * Creates a {@link javax.swing.JLabel} with the specified text, font and
     * color.
     * 
     * @param text
     *            the text
     * @param font
     *            the font
     * @param color
     *            the color
     * @return
     */
    public static JLabel createLabel(final String text, final Font font, final Color color) {
        final JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }
}