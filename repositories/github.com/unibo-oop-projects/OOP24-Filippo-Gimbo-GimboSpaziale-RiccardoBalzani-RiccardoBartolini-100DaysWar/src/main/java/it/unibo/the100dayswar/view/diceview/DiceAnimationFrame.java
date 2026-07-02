package it.unibo.the100dayswar.view.diceview;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import it.unibo.the100dayswar.commons.utilities.impl.IconLoader;

/**
 * A utility class that shows a dice animation in a JFrame.
 */
public final class DiceAnimationFrame {
    private static final Logger LOGGER = Logger.getLogger(DiceAnimationFrame.class.getName());

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    private static final String RESOURCES = "dice/";
    private static final String DICE_GIF = RESOURCES + "dice.gif";

    private static final int DURATION_MS = 3500;

    /**
     * Private constructor to prevent instantiation.
     */
    private DiceAnimationFrame() {
    }

    /**
     * Creates and configures a JFrame with the dice GIF.
     * The frame is not resizable, not manually closable,
     * and automatically closes after DURATION_MS milliseconds.
     *
     * @return the created JFrame (not yet visible).
     */
    private static JFrame createDiceFrame() {
        LOGGER.info("Creating Dice Animation Frame.");
        final JFrame frame = new JFrame("Rolling your dice...");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        final ImageIcon diceIcon = (ImageIcon) IconLoader.loadIcon(DICE_GIF);
        if (diceIcon != null) {
            final JLabel diceLabel = new JLabel(diceIcon);
            frame.add(diceLabel);
        } else {
            final JLabel errorLabel = new JLabel("Dice image not found.");
            frame.add(errorLabel);
            LOGGER.severe("Dice image not found at path: " + DICE_GIF);
        }

        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);

        final Timer closeTimer = new Timer(DURATION_MS, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                LOGGER.info("Closing Dice Animation Frame.");
                frame.dispose();
            }
        });
        closeTimer.setRepeats(false);
        closeTimer.start();
        LOGGER.info("Dice Animation Frame created successfully.");

        return frame;
    }

    /**
     * Creates, configures, and immediately shows the frame with the dice GIF.
     */
    public static void showDiceFrame() {
        SwingUtilities.invokeLater(() -> {
            final JFrame frame = createDiceFrame();
            frame.setVisible(true);
            LOGGER.info("Dice Animation Frame is now visible.");
        });
    }
}
