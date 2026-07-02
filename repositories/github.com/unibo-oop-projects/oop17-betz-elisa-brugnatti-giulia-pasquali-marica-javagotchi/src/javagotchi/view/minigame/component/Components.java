package javagotchi.view.minigame.component;

import java.awt.Color;
import java.awt.Dimension;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

import org.apache.commons.lang3.Pair;

import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.utility.Utility;

/**
 * 
 * @author marica
 *
 */
public final class Components {

    private Components() {
    }

    /**
     * Create a game button with coordinate.
     * 
     * @return instance of {@link GameButton}
     */
    public static Function<Pair<Integer, Integer>, GameButton> createGameButton() {
        return p -> new GameButton(p);
    }

    /**
     * Create a pause button.
     * 
     * @return pause button
     */
    public static JButton createPauseButton() {
        final int width = 50;
        final int height = 50;
        final String pathIcon = "/minigame/pause.png";
        final JButton pauseButton = new JButton(Utility.createIcon(pathIcon, width, height));

        pauseButton.setPreferredSize(new Dimension(width, height));
        pauseButton.setFocusable(false);
        pauseButton.setBorderPainted(false);
        pauseButton.setFocusPainted(false);
        pauseButton.setContentAreaFilled(false);

        pauseButton.addActionListener(e -> {
            Utility.log("Click Pause ...");
            MiniGame.getFactoryController().getControllerMiniGame().pauseGame();
        });

        return pauseButton;
    }

    /**
     * Create a progress time view.
     * 
     * @param width
     *            width of progress bar
     * @return progress time view
     */
    public static JProgressBar createTimeProgress(final int width) {

        final int min = 0;
        final int max = MiniGame.getFactoryController().getControllerMiniGame().getModel().getTime().getStartTime();
        final int height = 25;
        final JProgressBar progress = new JProgressBar();

        progress.setPreferredSize(new Dimension(width, height));
        progress.setValue(max);
        progress.setMinimum(min);
        progress.setMaximum(max);
        progress.setStringPainted(true);
        progress.setString(String.valueOf(max));
        progress.setBackground(Color.red);
        progress.setForeground(Color.green);

        progress.setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() {
                return Color.black;
            }

            protected Color getSelectionForeground() {
                return Color.black;
            }
        });

        return progress;
    }

    /**
     * Create a music button.
     * 
     * @return instance of {@link MusicButton}
     */
    public static MusicButton createMusicButton() {
        return new MusicButton();
    }

}
