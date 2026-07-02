package it.unibo.scat.view.components;

import java.awt.Dimension;
import java.awt.FontMetrics;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import it.unibo.scat.view.UIConstants;

/**
 * Class for custom text fields.
 */
public final class CustomTextField extends JTextField {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new text field with predefined styling and behavior.
     */
    public CustomTextField() {
        setFont(UIConstants.FONT_M);

        final FontMetrics fm = getFontMetrics(UIConstants.FONT_M);
        final int maxWidth = fm.charWidth('W') * 20 + getInsets().left
                + getInsets().right;
        final int maxHeight = fm.getHeight() * 2 + getInsets().top
                + getInsets().bottom;

        final Dimension d = new Dimension(maxWidth, maxHeight);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);

        setDocument(new PlainDocument() {
            @Override
            public void insertString(final int offs, final String str, final AttributeSet a)
                    throws BadLocationException {
                final int maxLength = 10;

                if (getLength() + str.length() <= maxLength) {
                    super.insertString(offs, str, a);
                }
            }
        });

    }
}
