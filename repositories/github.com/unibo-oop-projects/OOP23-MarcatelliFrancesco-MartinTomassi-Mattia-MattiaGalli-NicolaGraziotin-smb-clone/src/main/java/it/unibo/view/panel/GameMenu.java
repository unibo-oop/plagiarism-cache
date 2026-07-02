package it.unibo.view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.GameController;
import it.unibo.view.window.GameWindow.PanelType;

/**
 * The GameMenu class represents the panel of the main menu of the game.
 * It extends JPanel and provides buttons to switch to the GamePanel, check the scoreboard after a level and quit the application.
 */
@SuppressFBWarnings("DM_EXIT")
public class GameMenu extends JPanel {

    /**
     * Gap between JButton.
     */
    public static final int VERTICAL_GAP = 50;
    /**
     * Serial version UID for serialization/deserialization.
     * This constant is used to ensure version compatibility of serialized objects.
     */
    public static final long serialVersionUID = 1;
    /**
     * Represents the color of the buttons.
     */
    public static final Color BUTTON_COLOR = new Color(214, 47, 55);
    /**
     * Represents the title text font size.
     */
    public static final Font TITLE_TEXT_FONT = new Font("Arial", Font.BOLD, 50);
    /**
     * Represents the text font.
     */
    public static final Font TEXT_FONT = new Font("Arial", Font.BOLD, 30);
    private static final int ROWS = 4;
    private static final int COLS = 1;

    /**
     * Constructs a new instance of GameMenu with the specified GameController and GameWindowSwing.
     * 
     * @param controller the GameController associated with the window.
     */
    public GameMenu(final GameController controller) {
        this.setLayout(new GridLayout(ROWS, COLS, 0, VERTICAL_GAP));
        final JLabel title = new JLabel(new ImageIcon(getClass()
            .getClassLoader()
            .getResource("supermeatboyintro.png")));
        final JButton startButton = createButton("START", e -> {
            controller.start();
            controller.getGameWindow().switchPanel(PanelType.GAME);
        });

        final JButton scoreboardButton = createButton("SCOREBOARD", e -> {
                controller.getGameWindow().switchPanel(PanelType.SCOREBOARD);
        });

        final JButton quitButton = createButton("QUIT", e -> {
            if (JOptionPane.showConfirmDialog(null, "Sei sicuro di volere uscire?",
            "Esci dal gioco", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        this.setBackground(Color.BLACK);
        this.add(title);
        setupButton(startButton);
        setupButton(scoreboardButton);
        setupButton(quitButton);
    }

    private JButton createButton(final String text, final ActionListener actionListener) {
        final JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }

    private void setupButton(final JButton button) {
        button.setFont(TEXT_FONT);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.BLACK);
        this.add(button);
    }
}
