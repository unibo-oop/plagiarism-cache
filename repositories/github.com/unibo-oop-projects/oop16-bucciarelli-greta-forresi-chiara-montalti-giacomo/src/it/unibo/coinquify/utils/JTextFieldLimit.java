package it.unibo.coinquify.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Limit text field input.
 */
public class JTextFieldLimit extends PlainDocument {
    /**
     * 
     */
    private static final long serialVersionUID = -2259585443519230229L;

    private final int limit;

    /**
     * 
     * @param limit
     *            of field
     */
    public JTextFieldLimit(final int limit) {
        super();
        this.limit = limit;
    }

    @Override
    public void insertString(final int offset, final String str, final AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
