package it.unibo.plantsfarm.view.utility;

import it.unibo.plantsfarm.view.music.api.MusicPlayer;
import it.unibo.plantsfarm.view.music.impl.MusicPlayerImpl;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;

/**
 * Factory utility class for creating buttons.
 */
public final class ButtonFactory {

    private static final double FONT_SCALE_RATIO = 0.02;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int FONT_SIZE = (int) (SCREEN_HEIGHT * FONT_SCALE_RATIO);

    private static final Font MAIN_FONT = new Font("Arial", Font.BOLD, FONT_SIZE);
    private static final MusicPlayer AUDIO = new MusicPlayerImpl();

    private ButtonFactory() {
    }

    /**
     * Creates a standard text button.
     *
     * @param text The text to display on the button.
     * @return A JButton.
     */
    public static JButton createButton(final String text) {
        return configureButton(new JButton(text));
    }

    /**
     * Creates an icon button.
     *
     * @param icon The icon to display.
     * @return A JButton containing the icon.
     */
    public static JButton createMenuButton(final ImageIcon icon) {
        final JButton button = new JButton(icon);
        button.addActionListener(e -> AUDIO.playEffect("music/menuSound/click.wav"));
        return button;
    }

    /**
     * Helper method to configure the standard text button.
     *
     * @param button The button to configure.
     * @return The configured button.
     */
    private static JButton configureButton(final JButton button) {
        button.setFont(MAIN_FONT);
        button.setFocusPainted(false);
        //button.addActionListener(e -> AUDIO.playEffect("music/menuSound/click.wav"));
        return button;
    }
}
