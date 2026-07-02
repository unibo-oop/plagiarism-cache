package unibo.exiled.view;

import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.utilities.EndState;
import unibo.exiled.view.items.GameButton;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * This class represent the EndGameView view.
 */
public final class EndGameView {

    // MVC Components (MC)
    private final JFrame mainFrame;
    private final EndState state;

    // The button to quit the game.
    private final GameButton quitButton = new GameButton("Quit");

    /**
     * The constructor of the EndGameView view.
     *
     * @param state The end state of the game.
     */
    public EndGameView(final EndState state) {
        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle(ConstantsAndResourceLoader.GAME_NAME);
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);
        this.mainFrame.setLayout(new BorderLayout());

        this.state = state;

        this.initializeUI();
        this.initializeListeners();
    }

    private void initializeUI() {
        final JPanel endGamePanel = new JPanel(new BorderLayout());

        final JLabel endGameLabel = new JLabel(state.getEndStateImage());

        endGamePanel.add(endGameLabel, BorderLayout.CENTER);

        final JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(quitButton);
        endGamePanel.add(buttonsPanel, BorderLayout.SOUTH);

        this.mainFrame.getContentPane().add(endGamePanel);
    }

    private void initializeListeners() {
        quitButton.addActionListener(e -> {
            final int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Would you like to quit the game?", "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                mainFrame.dispatchEvent(new WindowEvent(this.mainFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    /**
     * This method makes the EndGameView view visible.
     */
    public void display() {
        this.mainFrame.setVisible(true);
    }
}
