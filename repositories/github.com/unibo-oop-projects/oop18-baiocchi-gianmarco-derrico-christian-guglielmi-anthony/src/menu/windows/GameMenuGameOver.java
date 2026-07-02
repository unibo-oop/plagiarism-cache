package menu.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import menu.buttons.ExitButton;
import menu.buttons.MainMenuButton;
import menu.components.GameFrameImpl;
import menu.components.GamePanelImpl;

/**
 * This is the game over menu where you can choose two different choices: main
 * menu or exits. This class is designed to automatically scale down based of
 * the main screen size. It implements specific game components that make
 * standard the overall design. For further information see
 * {@link GameFrameImpl}, {@link GamePanelImpl}, {@link GameLabelImpl},
 * {@link MainMenuButton} and {@link ExitButton} classes.
 */
public class GameMenuGameOver extends GameFrameImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int DISTANCE_BUTTON = 20;
    private static final URL IMG_URL_GAME_OVER = ClassLoader.getSystemResource("Game_Over_3.gif");
    private static final double SCALE = 0.6;

    /**
     * Creates a new GameMenuGameOver frame with default settings.
     */
    public GameMenuGameOver() {
        super(new BorderLayout());
        final JPanel northPanel = new GamePanelImpl(IMG_URL_GAME_OVER, SCALE, Image.SCALE_DEFAULT);
        final JPanel southPanel = new GamePanelImpl(new GridBagLayout());

        southPanel.setBackground(Color.BLACK);
        northPanel.setBackground(Color.BLACK);
        drawButtonInPanel(southPanel);

        super.add(northPanel, BorderLayout.NORTH);
        super.add(southPanel, BorderLayout.SOUTH);
        super.pack();
        super.setFrameInCenter(this.getWidth(), this.getHeight());
        super.setVisible(true);
    }

    private JFrame getFrame() {
        return this;
    }

    private void drawButtonInPanel(final JPanel panel) {
        final GridBagConstraints cnst = new GridBagConstraints();
        final JButton mainMenuButton = new MainMenuButton(getFrame());
        final JButton exitGameButton = new ExitButton();

        mainMenuButton.setBackground(Color.BLACK);
        exitGameButton.setBackground(Color.BLACK);

        cnst.insets = new Insets(DISTANCE_BUTTON, DISTANCE_BUTTON, DISTANCE_BUTTON, DISTANCE_BUTTON);
        cnst.gridx = 0;
        panel.add(mainMenuButton, cnst);
        cnst.gridx++;
        panel.add(exitGameButton, cnst);
    }
}
