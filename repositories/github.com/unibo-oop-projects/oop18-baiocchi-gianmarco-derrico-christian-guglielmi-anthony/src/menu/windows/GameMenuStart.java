package menu.windows;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import menu.buttons.ExitButton;
import menu.buttons.InfoButton;
import menu.buttons.NewGameButton;
import menu.components.GameFrameImpl;
import menu.components.GameLabelImpl;
import menu.components.GamePanelImpl;

/**
 * This is the initial game menu where you can choose different choices
 * including new game, options and exits. This class is designed to
 * automatically scale down based of the main screen size. It implements
 * specific game components that make standard the overall design. For further
 * information see {@link GameFrameImpl}, {@link GamePanelImpl},
 * {@link GameLabelImpl}, {@link NewGameButton}, {@link InfoButton} and
 * {@link ExitButton} classes.
 *
 */
public class GameMenuStart extends GameFrameImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int DISTANCE_BUTTON = 20;
    private static final URL IMG_URL_WEST = ClassLoader.getSystemResource("Logo iniziale.png");
    private static final URL IMG_URL_EAST = ClassLoader.getSystemResource("Bomberman_Background_East.png");
    private static final double SCALE_IMG_WEST = 0.7;
    private static final double SCALE_IMG_EAST = 0.3;

    /**
     * Creates a new GameMenuStart frame with default settings.
     */
    public GameMenuStart() {
        super();
        final JPanel eastPanel = new GamePanelImpl(new BorderLayout());
        final JPanel westPanel = new GamePanelImpl(IMG_URL_WEST, SCALE_IMG_WEST);
        final JPanel internalJpanel = new GamePanelImpl(new GridBagLayout());

        drawButtonInPanel(internalJpanel);
        eastPanel.add(new GameLabelImpl(IMG_URL_EAST, SCALE_IMG_EAST), BorderLayout.NORTH);
        eastPanel.add(internalJpanel, BorderLayout.CENTER);

        super.setLayout(new BorderLayout());
        super.add(westPanel, BorderLayout.WEST);
        super.add(eastPanel, BorderLayout.EAST);
        super.pack();
        super.setFrameInCenter(this.getWidth(), this.getHeight());
        super.setVisible(true);
    }

    private JFrame getFrame() {
        return this;
    }

    private void drawButtonInPanel(final JPanel panel) {
        final GridBagConstraints cnst = new GridBagConstraints();
        final JButton startGame = new NewGameButton(getFrame());
        final JButton optionGame = new InfoButton(getFrame());
        final JButton exitGame = new ExitButton();

        cnst.insets = new Insets(DISTANCE_BUTTON, DISTANCE_BUTTON, DISTANCE_BUTTON, DISTANCE_BUTTON);
        cnst.gridy = 0;
        panel.add(startGame, cnst);
        cnst.gridy++;
        panel.add(optionGame, cnst);
        cnst.gridy++;
        panel.add(exitGame, cnst);
    }
}
