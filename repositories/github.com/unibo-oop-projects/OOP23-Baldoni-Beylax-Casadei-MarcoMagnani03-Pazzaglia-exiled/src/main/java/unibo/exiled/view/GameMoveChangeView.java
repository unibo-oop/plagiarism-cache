package unibo.exiled.view;

import unibo.exiled.controller.GameController;
import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;


/**
 * This class represents the GameMoveChangeView view.
 */
public final class GameMoveChangeView {
    // MVC Components.
    private final JFrame mainFrame;
    private final GameView view;
    private final GameController gameController;

    /**
     * Constructs a GameMoveChangeView view.
     *
     * @param gameController the game controller associated with this view.
     * @param view           The main view, required to hide and show it.
     */
    public GameMoveChangeView(final GameController gameController, final GameView view) {
        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.mainFrame.setTitle(ConstantsAndResourceLoader.GAME_NAME);
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);
        this.mainFrame.setLayout(new BorderLayout());

        this.gameController = gameController;
        this.view = view;
        this.view.hide();
        initializeUI();
    }

    /**
     * Initializes the user interface of the GameMoveChangeView view.
     */
    private void initializeUI() {
        final String moveToLearn = gameController.getCharacterController().getPlayerExceedingMoveName();

        final JPanel gameMoveChangePanel = new JPanel(new BorderLayout());
        final JLabel gameMoveChangeImage = new JLabel(new ImageIcon(
                ClassLoader.getSystemResource(ConstantsAndResourceLoader.IMAGES_PATH + "/interface/changemove.png")));
        gameMoveChangePanel.add(gameMoveChangeImage, BorderLayout.NORTH);

        final JLabel gameMoveChangeLabel = new GameLabel(
                "Change a move from your MoveSet or refuse to learn the " + moveToLearn + " move.",
                SwingConstants.CENTER);
        gameMoveChangePanel.add(gameMoveChangeLabel, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel(new FlowLayout());

        for (final String move : gameController.getCharacterController().getPlayerMoveSet()) {
            final GameButton moveButton = getGameButton(move, moveToLearn);

            buttonPanel.add(moveButton);
        }

        final GameButton refuseButton = new GameButton("Abort");
        buttonPanel.add(refuseButton);
        refuseButton.addActionListener(e -> {
            final int dialogResult = JOptionPane.showConfirmDialog(this.mainFrame,
                    "Are you sure that you don't want to learn: " + moveToLearn, "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                this.mainFrame.dispose();
                this.view.display();
            }
        });
        gameMoveChangePanel.add(buttonPanel, BorderLayout.SOUTH);

        this.mainFrame.getContentPane().add(gameMoveChangePanel);
    }

    private GameButton getGameButton(final String move, final String moveToLearn) {
        final GameButton moveButton = new GameButton(move);

        moveButton.addActionListener(e -> {
            final String moveSelected = moveButton.getText();
            final int dialogResult = JOptionPane.showConfirmDialog(this.mainFrame,
                    "Would you like to change '" + moveSelected + "' into " + moveToLearn + "?", "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                this.gameController.getCharacterController().changeMove(moveSelected, moveToLearn);
                this.mainFrame.dispatchEvent(new WindowEvent(this.mainFrame, WindowEvent.WINDOW_CLOSING));
                this.view.display();
            }
        });
        return moveButton;
    }

    /**
     * Displays the GameMoveChangeView view.
     */
    public void display() {
        this.mainFrame.setVisible(true);
    }
}
