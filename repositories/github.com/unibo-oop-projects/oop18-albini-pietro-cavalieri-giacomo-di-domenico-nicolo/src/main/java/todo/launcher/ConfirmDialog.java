package todo.launcher;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A confirmation dialog is a message the launcher shows to the user, asking for
 * a yes and no answer.
 */
class ConfirmDialog {
    private final boolean answer;

    public ConfirmDialog(final String message, final Style style) {
        final JFrame frame = new JFrame();
        this.answer = JOptionPane.showConfirmDialog(frame, message, "//TODO", JOptionPane.YES_NO_OPTION,
                style.style) == JOptionPane.YES_OPTION;
    }

    /**
     * @return the answer the user chose
     */
    public boolean getAnswer() {
        return this.answer;
    }

    public enum Style {
        WARNING(JOptionPane.WARNING_MESSAGE);

        private final int style;

        Style(final int style) {
            this.style = style;
        }
    }
}
