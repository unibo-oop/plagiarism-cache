package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

//import controller.GameEngine;
/**
 * Panel on which display the time elapsed and the score during the game it will
 * be shown on the GameFrame beside the GameScreenPanel
 */
public class ScoreLifePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Font font = new Font("Courier New", Font.BOLD, 16);

    private static JLabel score;

    /**
     * Creates a new Panel.
     * 
     */
    public ScoreLifePanel() {

        score = new JLabel();
        score.setFont(this.font);

        setLayout(new GridLayout(0, 1));
        setBackground(Color.RED);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(score);
        updateScore(0,0);
    }

    /**
     * Updates the score.
     * 
     * @param score
     *            the current score of the player
     */
    public static void updateScore(final int lives, final int score) {
        SwingUtilities.invokeLater(() -> {
            ScoreLifePanel.score.setText("Lives: " + lives + " | Score: " + score);
        });
    }

}
