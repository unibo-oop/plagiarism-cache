package it.unibo.plantsfarm.view.utility;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Factory for creating menu buttons windows.
 */
public final class WindowFactory {

    private static final double SCREEN_RATIO = 1.45;

    private WindowFactory() { }

    /**
     * Creates a window.
     *
     * @param title The title of the window.
     * @return A JDialog.
     */
    public static JDialog createMenuWindow(final String title) {
        final JDialog dialog = new JDialog((JFrame) null, title, true);

        dialog.setResizable(false);

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) (screenSize.width / SCREEN_RATIO);
        final int height = (int) (screenSize.height / SCREEN_RATIO);

        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        return dialog;
    }
}
